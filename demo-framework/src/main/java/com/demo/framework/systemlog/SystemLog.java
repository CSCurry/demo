package com.demo.framework.systemlog;

import java.lang.annotation.*;

/**
 * 打印接口请求信息日志
 *
 * @author 30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

}