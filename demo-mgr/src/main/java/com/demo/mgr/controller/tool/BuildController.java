package com.demo.mgr.controller.tool;

import com.demo.framework.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * build表单构建
 *
 * @author 30
 */
@Controller
@RequestMapping("/tool/build")
public class BuildController extends BaseController {

    @RequiresPermissions("tool:build:view")
    @GetMapping()
    public String build() {
        return "tool/build/build";
    }
}
