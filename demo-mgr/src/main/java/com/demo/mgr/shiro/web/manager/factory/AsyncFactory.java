package com.demo.mgr.shiro.web.manager.factory;

import com.demo.business.domain.SysLogininfor;
import com.demo.business.domain.SysOperLog;
import com.demo.business.domain.SysUserOnline;
import com.demo.business.service.mgr.ISysOperLogService;
import com.demo.business.service.mgr.ISysUserOnlineService;
import com.demo.business.service.mgr.impl.SysLogininforServiceImpl;
import com.demo.framework.constant.Constant;
import com.demo.framework.util.*;
import com.demo.mgr.shiro.ShiroUtils;
import com.demo.mgr.shiro.session.OnlineSession;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author 30
 */
@Slf4j
public class AsyncFactory {

    /**
     * 同步session到数据库
     *
     * @param session 在线用户会话
     * @return 任务task
     */
    public static TimerTask syncSessionToDb(final OnlineSession session) {
        return new TimerTask() {
            @Override
            public void run() {
                SysUserOnline online = new SysUserOnline();
                online.setSessionId(String.valueOf(session.getId()));
                online.setDeptName(session.getDeptName());
                online.setLoginName(session.getLoginName());
                online.setStartTimestamp(session.getStartTimestamp());
                online.setLastAccessTime(session.getLastAccessTime());
                online.setExpireTime(session.getTimeout());
                online.setIpaddr(session.getHost());
                online.setLoginLocation(IpUtil.getRealAddressByIP(session.getHost()));
                online.setBrowser(session.getBrowser());
                online.setOs(session.getOs());
                online.setStatus(session.getStatus());
                SpringUtil.getBean(ISysUserOnlineService.class).saveOnline(online);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(IpUtil.getRealAddressByIP(operLog.getOperIp()));
                SpringUtil.getBean(ISysOperLogService.class).insertOperlog(operLog);
            }
        };
    }

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message, final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
        final String ip = ShiroUtils.getIp();
        return new TimerTask() {
            @Override
            public void run() {
                String address = IpUtil.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(StringUtil.getBlock(ip));
                s.append(address);
                s.append(StringUtil.getBlock(username));
                s.append(StringUtil.getBlock(status));
                s.append(StringUtil.getBlock(message));
                // 打印信息到日志
                log.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfor loginInfo = new SysLogininfor();
                loginInfo.setLoginName(username);
                loginInfo.setIpaddr(ip);
                loginInfo.setLoginLocation(address);
                loginInfo.setBrowser(browser);
                loginInfo.setOs(os);
                loginInfo.setMsg(message);
                // 日志状态
                if (StringUtil.equalsAny(status, Constant.LOGIN_SUCCESS, Constant.LOGOUT, Constant.REGISTER)) {
                    loginInfo.setStatus("0");
                } else if (Constant.LOGIN_FAIL.equals(status)) {
                    loginInfo.setStatus("1");
                }
                // 插入数据
                SpringUtil.getBean(SysLogininforServiceImpl.class).insertLogininfor(loginInfo);
            }
        };
    }
}
