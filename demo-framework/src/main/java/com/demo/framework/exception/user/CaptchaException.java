package com.demo.framework.exception.user;

/**
 * 验证码错误异常类
 *
 * @author 30
 */
public class CaptchaException extends UserException {

    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.captcha.error", null);
    }
}
