package com.demo.common.exception.file;

import com.demo.common.exception.BaseException;

/**
 * 文件信息异常类
 *
 * @author 30
 */
public class FileException extends BaseException {

    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
