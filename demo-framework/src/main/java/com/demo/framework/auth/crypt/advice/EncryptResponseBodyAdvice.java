package com.demo.framework.auth.crypt.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.framework.auth.crypt.annotation.Encrypt;
import com.demo.framework.auth.util.AESUtil;
import com.demo.framework.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * ResponseBody解密通知
 *
 * @author 30
 */
@Slf4j
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static ThreadLocal<Boolean> encryptLocal = new ThreadLocal<>();

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(Encrypt.class);
    }

    /**
     * supports方法返回true才会进入此方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        Boolean status = encryptLocal.get();
        if (null != status && !status) {
            encryptLocal.remove();
            return body;
        }
        try {
            //响应数据
            String data = JSON.toJSONString(body, SerializerFeature.WriteMapNullValue);
            //aesKey加密数据
            HttpServletRequest req = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String aesKey = String.valueOf(req.getAttribute("aesKey"));
            String encryptedData = AESUtil.encrypt(data, aesKey);

            log.info("接口：{}", req.getRequestURI());
            log.info("响应数据加密前：{}", data);
            log.info("当前request域的aesKey：{}", aesKey);
            log.info("响应数据加密后：{}", encryptedData);

            return encryptedData;
        } catch (Exception e) {
            log.error("加密异常", e);
            throw new EncryptException(e.getMessage());
        }
        //return body;
    }
}