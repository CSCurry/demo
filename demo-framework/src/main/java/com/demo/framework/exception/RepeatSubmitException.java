package com.demo.framework.exception;

/**
 * 重复请求异常
 */
public class RepeatSubmitException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RepeatSubmitException() {
        super();
    }

    public RepeatSubmitException(String message) {
        super(message);
    }
}