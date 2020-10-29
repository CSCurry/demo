package com.demo.framework.systemlog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.framework.util.IpUtil;
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
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统接口日志切点
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
    @Pointcut("@annotation(systemLog)")
    public void pointCut(SystemLog systemLog) {
    }

    /**
     * 前置通知，方法调用前被调用
     */
    @Before("pointCut(SystemLog)")
    public void doBefore() {
        timeThreadLocal.set(System.currentTimeMillis());
    }

    /**
     * 后置返回通知
     */
    @AfterReturning(value = "pointCut(systemLog)", returning = "result", argNames = "joinPoint,result,systemLog")
    public void doAfterReturning(JoinPoint joinPoint, Object result, SystemLog systemLog) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String method = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();

            //是否打印接口请求参数
            boolean showParams = systemLog.showParams();
            //请求参数
            Object params = null;
            Object[] args = joinPoint.getArgs();
            if (showParams && args != null && args.length > 0) {
                //joinPoint.getArgs()返回的数组中携带有Request或Response对象，导致JSON.toJSONString时序列化异常：
                //java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //过滤Request和Response后序列化无异常
                List<Object> argList = Arrays.stream(args).filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse))).collect(Collectors.toList());
                params = argList.isEmpty() ? null : argList.get(0);
            }

            HashMap<String, Object> map = new LinkedHashMap<>();
            map.put("time", (System.currentTimeMillis() - timeThreadLocal.get()) + "ms");
            map.put("method", method);
            if (showParams) {
                map.put("params", params);
            }
            if (systemLog.showResult()) {
                map.put("result", result);
            }
            map.put("uri", request.getRequestURI());
            map.put("ip", IpUtil.getIpAddr(request));
            log.info(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
        } finally {
            timeThreadLocal.remove();
        }
    }
}