package com.demo.web.controller.test;

import com.demo.common.base.BaseController;
import com.demo.common.base.BaseResult;
import com.demo.common.base.BaseResultVO;
import com.demo.common.entity.DemoUser;
import com.demo.framework.systemlog.SystemLog;
import com.demo.service.demo.service.DemoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "测试控制器 - db")
@RequestMapping("test/db")
public class TestDBController extends BaseController {

    @Resource
    private DemoService testService;

    @PostMapping("addUser")
    public BaseResult addUser() {
        DemoUser user = new DemoUser();
        user.setAge(12);
        user.setHeight(180.8);
        user.setName("Tony");
        int i = testService.addUser(user);
        return BaseResult.success(i);
    }

    @SystemLog
    @PostMapping("getAll")
    public BaseResultVO<DemoUser> getAll() {
        List<DemoUser> list = testService.getAll();
        return BaseResultVO.success(list);
    }

    @SystemLog
    @PostMapping("pageData")
    public BaseResultVO<PageInfo<DemoUser>> pageData(@RequestBody DemoUser user) {
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        List<DemoUser> list = testService.getAll();
        PageInfo<DemoUser> pageInfo = new PageInfo<>(list);
        return BaseResultVO.success(pageInfo);
    }

}