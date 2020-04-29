package com.demo.web.controller.test;

import com.demo.common.base.BaseController;
import com.demo.common.base.BaseResult;
import com.demo.framework.auth.crypt.annotation.Decrypt;
import com.demo.framework.auth.crypt.annotation.Encrypt;
import com.demo.framework.systemlog.SystemLog;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "测试控制器 - Crypt")
@RequestMapping("test/crypt")
public class TestCryptController extends BaseController {

    @SystemLog
    @Decrypt
    @PostMapping("decrypt")
    public BaseResult decrypt(@RequestBody String data) {
        System.out.println("进入controller方法");
        System.out.println("数据：" + data);
        return BaseResult.success();
    }

    @Encrypt
    @PostMapping("encrypt")
    public BaseResult encrypt(@RequestBody String data) {
        System.out.println("进入controller方法");
        System.out.println("数据：" + data);
        return BaseResult.success();
    }

    @SystemLog
    @Decrypt
    @Encrypt
    @PostMapping("decryptEncrypt")
    public BaseResult decryptEncrypt(@RequestBody String data) {
        System.out.println("进入controller方法");
        System.out.println("数据：" + data);
        return BaseResult.success();
    }

    @Encrypt
    @Decrypt(notEmpty = true)
    @PostMapping("checkoutParam")
    public BaseResult checkoutParam(@RequestBody String data) {
        System.out.println("进入controller方法");
        System.out.println("数据：" + data);
        return BaseResult.success();
    }

}