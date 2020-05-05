package com.demo.mgr.controller.system;

import com.demo.business.domain.SysUser;
import com.demo.business.service.mgr.ISysConfigService;
import com.demo.mgr.shiro.service.SysRegisterService;
import com.demo.common.base.AjaxResult;
import com.demo.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 注册验证
 *
 * @author 30
 */
@Controller
public class SysRegisterController extends BaseController {

    @Resource
    private SysRegisterService registerService;
    @Resource
    private ISysConfigService configService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
