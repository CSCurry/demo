package com.demo.mgr.controller.system;

import com.demo.framework.base.AjaxResult;
import com.demo.framework.base.BaseController;
import com.demo.framework.systemlog.SystemLog;
import com.demo.framework.util.ServletUtil;
import com.demo.framework.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录验证
 *
 * @author 30
 */
@Controller
public class SysLoginController extends BaseController {

    @GetMapping("/login")
    public String login() {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtil.isAjaxRequest(getRequest())) {
            return ServletUtil.renderString(getResponse(), "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        return "login";
    }

    @SystemLog
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtil.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
