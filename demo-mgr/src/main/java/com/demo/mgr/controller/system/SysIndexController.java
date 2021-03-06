package com.demo.mgr.controller.system;

import com.demo.business.domain.SysMenu;
import com.demo.business.domain.SysUser;
import com.demo.business.service.mgr.ISysConfigService;
import com.demo.business.service.mgr.ISysMenuService;
import com.demo.framework.base.BaseController;
import com.demo.framework.config.GlobalConfig;
import com.demo.framework.constant.Constant;
import com.demo.mgr.shiro.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页 业务处理
 *
 * @author 30
 */
@Controller
public class SysIndexController extends BaseController {

    @Resource
    private ISysMenuService menuService;
    @Resource
    private ISysConfigService configService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey(Constant.CONFIG_KEY_SIDE_THEME));
        mmap.put("skinName", configService.selectConfigByKey(Constant.CONFIG_KEY_SKIN_NAME));
        mmap.put("copyrightYear", GlobalConfig.getCopyrightYear());
        mmap.put("demoEnabled", GlobalConfig.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin() {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        mmap.put("version", GlobalConfig.getVersion());
        return "main";
    }
}
