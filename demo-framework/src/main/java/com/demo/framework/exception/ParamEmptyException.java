package com.demo.framework.exception;

/**
 * 参数为空异常
 */
public class ParamEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParamEmptyException() {
        super();
    }

    public ParamEmptyException(String message) {
        super(message);
    }
}