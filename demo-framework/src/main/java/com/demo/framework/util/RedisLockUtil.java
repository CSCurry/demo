package com.demo.framework.util;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * Redis分布式锁
 * <p>
 * 参考@see <a href="https://mp.weixin.qq.com/s/rodHnbEF_cBjV31-5N0t5w">Redis分布式锁的正确实现方式</a>
 * 参考@see <a href="https://www.cnblogs.com/linjiqin/p/8003838.html">Redis分布式锁的正确实现方式</a>
 */
@SuppressWarnings("UnusedReturnValue")
@Component
public class RedisLockUtil {

    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    //Lua脚本，意思是获取锁对应的value值，检查是否与clientId相等，如果相等则删除锁（解锁）。
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁
     * 1.可实现分布式加锁，支持重复，线程安全
     * 2.仅针对单实例Redis，对于Redis集群则无法使用
     *
     * @param lockKey  加锁键
     * @param clientId 加锁客户端唯一标识
     * @param seconds  锁过期时间
     * @return true成功，false失败
     */
    public boolean lock(String lockKey, String clientId, int seconds) {
        return stringRedisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            //nx：意思是set if not exists，也就是只有key不存在的时候才设置
            //ex(int secondsToExpire)参数时间单位为秒，px(long millisecondsToExpire)参数时间单位为毫秒
            String result = jedis.set(lockKey, clientId, new SetParams().nx().ex(seconds));
            return LOCK_SUCCESS.equals(result);
        });
    }

    /**
     * 释放锁，与lock相对应
     *
     * @param lockKey  加锁键
     * @param clientId 加锁客户端唯一标识
     * @return true成功，false失败
     */
    public boolean releaseLock(String lockKey, String clientId) {
        return stringRedisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            Object result = jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(lockKey), Collections.singletonList(clientId));
            return RELEASE_SUCCESS.equals(result);
        });
    }
}
