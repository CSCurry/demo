package com.demo.framework.auth.crypt.advice;

import com.demo.framework.auth.crypt.annotation.Decrypt;
import com.demo.framework.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * RequestBody解密通知
 *
 * @author 30
 */
@Slf4j
@ControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(Decrypt.class);
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type type, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    /**
     * supports方法返回true才会进入此方法
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type type, Class<? extends HttpMessageConverter<?>> converterType) {
        return new DecryptHttpInputMessage(inputMessage, parameter, redisUtil);
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type type, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}