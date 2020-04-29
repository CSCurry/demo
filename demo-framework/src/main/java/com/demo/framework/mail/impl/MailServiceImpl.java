package com.demo.framework.mail.impl;

import com.demo.framework.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    //SpringBoot提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
    @Resource
    private JavaMailSender mailSender;

    //发送方邮箱
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        try {
            //创建SimpleMailMessage对象
            SimpleMailMessage message = new SimpleMailMessage();
            //邮件发送人
            message.setFrom(from);
            //邮件接收人
            message.setTo(to);
            //邮件主题
            message.setSubject(subject);
            //邮件内容
            message.setText(content);
            //发送邮件
            mailSender.send(message);

            log.info("发送邮件成功：{}", to);
        } catch (MailException e) {
            log.error("发送邮件异常", e);
        }
    }

    /**
     * 发送html邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content html内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        try {
            //获取MimeMessage对象
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper;
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(from);
            //邮件接收人
            messageHelper.setTo(to);
            //邮件主题
            message.setSubject(subject);
            //邮件内容
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);

            log.info("发送邮件成功：{}", to);
        } catch (MessagingException e) {
            log.error("发送邮件异常", e);
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  html内容
     * @param filePath 附件路径
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        try {
            //获取MimeMessage对象
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            log.info("发送邮件成功：{}", to);
        } catch (MessagingException e) {
            log.error("发送邮件异常", e);
        }
    }
}