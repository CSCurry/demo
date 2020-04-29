package com.demo.framework.repeatsubmit.aspect;

import com.demo.framework.repeatsubmit.annotation.NoRepeatSubmit;
import com.demo.framework.exception.RepeatSubmitException;
import com.demo.framework.util.RedisLockUtil;
import com.demo.framework.util.UUIDUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 防止用户重复点击重复提交请求
 * 1.自定义注解@NoRepeatSubmit标记Controller中需要禁止重复提交的请求。
 * 2.通过AOP对所有标记了@NoRepeatSubmit的方法进行拦截。
 * 3.在业务方法执行前，获取当前用户的token或者JSessionId+当前请求地址，作为一个唯一的key，去获取redis分布式锁，如果此时并发获取，只有一个线程能获取到。
 * 4.在业务方法执行后，释放锁。
 * 参考@see <a href="https://blog.csdn.net/a992795427/article/details/92834286">SpringBoot利用AOP防止请求重复提交</a>
 */
@Aspect
@Component
public class RepeatSubmitAspect {

    @Resource
    private RedisLockUtil redisLockUtil;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointCut(NoRepeatSubmit noRepeatSubmit) {
    }

    @Around(value = "pointCut(noRepeatSubmit)", argNames = "pjp,noRepeatSubmit")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {
        //key=sessionId+path，也可以token+path
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String sessionId = request.getSession().getId();
        String path = request.getServletPath();
        String key = sessionId + path;
        //clientId用UUID
        String clientId = UUIDUtil.getUUID();

        boolean isSuccess = redisLockUtil.lock(key, clientId, noRepeatSubmit.lockTime());
        if (isSuccess) {
            //获取锁成功
            Object result;
            try {
                //执行进程
                result = pjp.proceed();
            } finally {
                //解锁
                redisLockUtil.releaseLock(key, clientId);
            }
            return result;
        } else {
            //获取锁失败，是重复提交的请求
            throw new RepeatSubmitException();
        }
    }
}