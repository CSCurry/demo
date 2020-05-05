package com.demo.mgr.shiro.service;

import com.demo.business.domain.SysUserOnline;
import com.demo.business.service.mgr.ISysUserOnlineService;
import com.demo.mgr.shiro.session.OnlineSession;
import com.demo.framework.util.StringUtil;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 会话db操作处理
 *
 * @author 30
 */
@Component
public class SysShiroService {

    @Resource
    private ISysUserOnlineService onlineService;

    /**
     * 删除会话
     *
     * @param onlineSession 会话信息
     */
    public void deleteSession(OnlineSession onlineSession) {
        onlineService.deleteOnlineById(String.valueOf(onlineSession.getId()));
    }

    /**
     * 获取会话信息
     *
     * @param sessionId 会话ID
     * @return Session
     */
    public Session getSession(Serializable sessionId) {
        SysUserOnline userOnline = onlineService.selectOnlineById(String.valueOf(sessionId));
        return StringUtil.isNull(userOnline) ? null : createSession(userOnline);
    }

    public Session createSession(SysUserOnline userOnline) {
        OnlineSession onlineSession = new OnlineSession();
        if (StringUtil.isNotNull(userOnline)) {
            onlineSession.setId(userOnline.getSessionId());
            onlineSession.setHost(userOnline.getIpaddr());
            onlineSession.setBrowser(userOnline.getBrowser());
            onlineSession.setOs(userOnline.getOs());
            onlineSession.setDeptName(userOnline.getDeptName());
            onlineSession.setLoginName(userOnline.getLoginName());
            onlineSession.setStartTimestamp(userOnline.getStartTimestamp());
            onlineSession.setLastAccessTime(userOnline.getLastAccessTime());
            onlineSession.setTimeout(userOnline.getExpireTime());
        }
        return onlineSession;
    }
}
