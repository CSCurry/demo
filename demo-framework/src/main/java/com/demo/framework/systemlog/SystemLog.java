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

    /**
     * 是否打印接口请求参数
     */
    boolean showParams() default true;

    /**
     * 是否打印接口返回数据
     */
    boolean showResult() default true;
}