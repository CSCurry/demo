package com.demo.framework.exception.user;

import com.demo.framework.exception.BaseException;

/**
 * 用户信息异常类
 *
 * @author 30
 */
public class UserException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
