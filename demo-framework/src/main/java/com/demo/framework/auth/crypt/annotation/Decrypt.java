package com.demo.framework.auth.crypt.annotation;

import java.lang.annotation.*;

/**
 * 加在controller方法上，表示该接口请求数据RequestBody在客户端已加密，需要解密
 *
 * @author 30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {

    //数据是否不能为null或空串，默认false
    boolean notEmpty() default false;
}