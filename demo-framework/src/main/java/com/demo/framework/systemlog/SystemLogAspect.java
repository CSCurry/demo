package com.demo.framework.systemlog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.framework.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * 系统日志切点
 *
 * @author 30
 */
@Slf4j
@Aspect
@Component
public class SystemLogAspect {

    /**
     * ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     * <p>
     * void set(Object value)设置当前线程的线程局部变量的值。
     * public Object get()该方法返回当前线程所对应的线程局部变量。
     * public void remove()将当前线程局部变量的值删除，目的是为了减少内存的占用。需要说明的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。
     * protected Object initialValue()返回该线程局部变量的初始值。
     */
    ThreadLocal<Long> timeThreadLocal = new ThreadLocal<>();

    /**
     * Controller层切点
     */
    @Pointcut("@annotation(com.demo.framework.systemlog.SystemLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知，方法调用前被调用
     */
    @Before("controllerAspect()")
    public void doBefore() {
        timeThreadLocal.set(System.currentTimeMillis());
    }

    /**
     * 后置返回通知
     */
    @AfterReturning(pointcut = "controllerAspect()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            HashMap<String, Object> map = new LinkedHashMap<>();
            map.put("time", (System.currentTimeMillis() - timeThreadLocal.get()) + "ms");
            map.put("method", joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName());
            map.put("params", joinPoint.getArgs() == null || joinPoint.getArgs().length == 0 ? null : joinPoint.getArgs()[0]);
            map.put("result", result);
            map.put("uri", request.getRequestURI());
            map.put("ip", IPUtil.getIp(request));
            log.info(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
        } finally {
            timeThreadLocal.remove();
        }
    }
}