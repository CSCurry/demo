package com.demo.framework.repeatsubmit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加在controller方法上，表示该接口请求在响应完成之前不允许重复提交
 *
 * @author 30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    /**
     * 锁定时间（单位秒）
     * !!!该时间应该设置得比请求响应时间更长，否则可能还未响应的时候就已释放了锁，此时二次请求是可以通过的
     */
    int lockTime() default 30;
}