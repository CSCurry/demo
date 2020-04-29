package com.demo.framework.global;

import com.demo.common.base.BaseResult;
import com.demo.common.enums.ResultCode;
import com.demo.framework.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception
     */
    @ExceptionHandler(Exception.class)
    public BaseResult exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResult.error();
    }

    /**
     * RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResult runtimeExceptionHandler(RuntimeException e) {
        log.error("运行时异常：", e);
        return BaseResult.error();
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public BaseResult httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return BaseResult.error("不支持" + e.getMethod() + "请求");
    }

    /**
     * validation参数校验失败异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        //从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        //然后提取错误提示信息进行返回
        return BaseResult.errorParam(objectError.getDefaultMessage());
    }

    /**
     * DecryptException
     */
    @ExceptionHandler(DecryptException.class)
    public BaseResult decryptExceptionHandler(DecryptException e) {
        log.error(e.getMessage(), e);
        return BaseResult.error(ResultCode.DATA_DECRYPT_ERROR);
    }

    /**
     * EncryptException
     */
    @ExceptionHandler(EncryptException.class)
    public BaseResult decryptExceptionHandler(EncryptException e) {
        log.error(e.getMessage(), e);
        return BaseResult.error(ResultCode.DATA_ENCRYPT_ERROR);
    }

    /**
     * ParamEmptyException
     */
    @ExceptionHandler(ParamEmptyException.class)
    public BaseResult paramEmptyExceptionHandler(ParamEmptyException e) {
        return BaseResult.error(ResultCode.PARAM_EMPTY);
    }

    /**
     * RepeatSubmitException
     */
    @ExceptionHandler(RepeatSubmitException.class)
    public BaseResult repeatSubmitExceptionHandler(RepeatSubmitException e) {
        return BaseResult.error(ResultCode.REPEAT_SUBMIT);
    }

    /**
     * AuthException
     */
    @ExceptionHandler(AuthException.class)
    public BaseResult authExceptionHandler(AuthException e) {
        return BaseResult.error(ResultCode.ACCESS_DENIED);
    }
}
