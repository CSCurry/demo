package com.demo.web.controller.test;

import com.demo.framework.base.BaseController;
import com.demo.framework.base.BaseResult;
import com.demo.framework.repeatsubmit.annotation.NoRepeatSubmit;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "测试控制器 - Repeat")
@RequestMapping("test/repeat")
public class TestRepeatController extends BaseController {

    @PostMapping("repeatSubmit")
    public BaseResult repeatSubmit() {
        System.out.println("进入方法，休眠1秒");
        System.out.println(getSession().getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("休眠结束");
        return BaseResult.success();
    }

    @NoRepeatSubmit
    @PostMapping("noRepeatSubmit")
    public BaseResult noRepeatSubmit() {
        System.out.println("进入方法，休眠1秒");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("休眠结束");
        return BaseResult.success();
    }
}