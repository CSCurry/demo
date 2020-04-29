package com.demo.framework.exception;

/**
 * 解密异常
 */
public class DecryptException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DecryptException() {
        super();
    }

    public DecryptException(String message) {
        super(message);
    }
}