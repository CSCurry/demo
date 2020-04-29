package com.demo.framework.auth.crypt.annotation;

import java.lang.annotation.*;

/**
 * 加在controller方法上，表示该接口返回数据ResponseBody会加密，需要客户端解密
 *
 * @author 30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {

}