package com.demo.web.controller.test;

import com.demo.common.base.BaseController;
import com.demo.framework.mail.MailService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@Api(tags = "测试控制器 - Mail")
@RequestMapping("test/mail")
public class TestMailController extends BaseController {

    @Resource
    private MailService mailService;

    private String testMail = "277592046@qq.com";

    @PostMapping("sendSimpleMail")
    public void sendSimpleMail() {
        mailService.sendSimpleMail(testMail, "你好，普通邮件", "第一封邮件");
    }

    @PostMapping("sendHtmlMail")
    public void sendHtmlMail() {
        mailService.sendHtmlMail(testMail, "你好，html邮件", "<h1>第一封html邮件</h1><a href='www.baidu.com'>点我</a>");
    }
}