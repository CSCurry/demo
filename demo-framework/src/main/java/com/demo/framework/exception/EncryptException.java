package com.demo.framework.exception;

/**
 * 加密异常
 */
public class EncryptException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EncryptException() {
        super();
    }

    public EncryptException(String message) {
        super(message);
    }
}