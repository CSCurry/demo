package com.demo.framework.exception;

/**
 * 鉴权
 */
public class AuthException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthException() {
        super();
    }

    public AuthException(String message) {
        super(message);
    }
}