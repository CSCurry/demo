package com.demo.framework.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * RedisUtil
 *
 * @author 30
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtil() {
    }

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 判断key是否存在
     */
    public boolean hasExist(String key) {
        Boolean b = redisTemplate.hasKey(key);
        return b == null ? false : b;
    }

    /**
     * 根据key指定过期时间
     *
     * @param key  键
     * @param time 时间(秒)，覆盖原过期时间，key不存在返回false
     */
    public boolean setExpire(String key, long time) {
        Boolean b = redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return b == null ? false : b;
    }

    /**
     * 根据key获取过期时间
     *
     * @return 时间(秒)，返回-1表示不会过期，返回-2表示key不存在
     */
    public long getExpire(String key) {
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire == null ? 0 : expire;
    }

    /**
     * 删除缓存 - 单个
     *
     * @return 删除结果。key不存在返回false
     */
    public boolean delete(String key) {
        Boolean b = redisTemplate.delete(key);
        return b == null ? false : b;
    }

    /**
     * 删除缓存 - 多个
     *
     * @return 删除成功的数量
     */
    @SuppressWarnings("unchecked")
    public int delete(String... key) {
        Long count = redisTemplate.delete(CollectionUtils.arrayToList(key));
        return count == null ? 0 : count.intValue();
    }

    /**
     * 模糊查询获取key值
     * !!!慎用，不安全，keys的操作会导致数据库暂时被锁住，其他的请求都会被堵塞，业务量大的时候会出问题
     *
     * @param pattern "*"查询所有key
     */
    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }
    /* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ String ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */

    /**
     * 存String
     */
    public boolean setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    /**
     * 存String，并设置过期时间
     *
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public boolean setString(String key, String value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return true;
        } else {
            return setString(key, value);
        }
    }

    /**
     * 取String
     *
     * @return value。key不存在则返回null
     */
    public String getString(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o == null ? null : String.valueOf(o);
    }

    /**
     * 递增
     * !!!若键不存在，则初始值为0，再做增量操作
     * !!!若键值存在但值不是integer，会报错
     *
     * @param key 键
     * @param i   增量（要加几），若为负数，实为减少
     * @return 递增后的值
     */
    public Long increment(String key, int i) {
        return redisTemplate.opsForValue().increment(key, i);
    }

    /**
     * 递减
     * !!!若键不存在，则初始值为0，再做减量操作
     * !!!若键值存在但值不是integer，会报错
     *
     * @param key 键
     * @param i   减量（要减少几），若为负数，实为增加
     * @return 递减后的值
     */
    public Long decrement(String key, int i) {
        return redisTemplate.opsForValue().decrement(key, i);
    }
    /* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ String ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

    /* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ hash ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */

    /**
     * 存hash
     */
    public boolean setHash(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
        return true;
    }

    /**
     * 存hash，并设置过期时间
     *
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public boolean setHash(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        if (time > 0) {
            setExpire(key, time);
        }
        return true;
    }

    /**
     * hash中添加单个字段和值，如果不存在将创建
     *
     * @param key   键
     * @param field 字段名
     * @param value 字段对应的值
     */
    public boolean setHashFieldValue(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
        return true;
    }

    /**
     * 取hash
     */
    public Map<Object, Object> getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 取hash中的单个字段对应值
     *
     * @param key   键
     * @param field 字段名
     * @return 字段对应的值
     */
    public Object getHashFieldValue(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 判断hash中是否有该字段
     */
    public boolean hasExistHashField(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 删除hash中的字段
     *
     * @param key    键
     * @param fields 字段名
     * @return 成功个数
     */
    public int deleteHashField(String key, Object... fields) {
        Long count = redisTemplate.opsForHash().delete(key, fields);
        return count.intValue();
    }

    /**
     * hash递增
     *
     * @param key   键
     * @param field 字段名
     * @param i     增量（要加几），若为负数，实为减少
     * @return 递增后的值
     */
    public double incrementHash(String key, String field, double i) {
        return redisTemplate.opsForHash().increment(key, field, i);
    }

    /**
     * hash递减
     *
     * @param key   键
     * @param field 字段名
     * @param i     减量（要减几），若为负数，实为增加
     * @return 递减后的值
     */
    public double decrementHash(String key, String field, double i) {
        return redisTemplate.opsForHash().increment(key, field, -i);
    }
    /* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ hash ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

    /* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ set ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */

    /**
     * 存set
     *
     * @return 成功个数
     */
    public int setSet(String key, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        return count == null ? 0 : count.intValue();
    }

    /**
     * 存set，并设置过期时间
     *
     * @return 成功个数
     */
    public int setSet(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (time > 0) {
            setExpire(key, time);
        }
        return count == null ? 0 : count.intValue();

    }

    /**
     * 取set
     */
    public Set<Object> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 获取set的长度
     */
    public int getSetSize(String key) {
        Long size = redisTemplate.opsForSet().size(key);
        return size == null ? 0 : size.intValue();
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true存在、false不存在
     */
    public boolean hasExistSetMember(String key, Object value) {
        Boolean b = redisTemplate.opsForSet().isMember(key, value);
        return b == null ? false : b;
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public int deleteSetMember(String key, Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return count == null ? 0 : count.intValue();
    }
    /* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ set ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

    /* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ list ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */

    /**
     * 取list中的一个元素 - 根据索引值
     *
     * @param key   键
     * @param index 索引（index>=0时：0表头，1第二个元素，依次类推；index<0时：-1表尾，-2倒数第二个元素，依次类推）
     */
    public Object getListByIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 取list中的多个元素 - 根据索引范围
     *
     * @param key   键
     * @param start 开始
     * @param end   结束（0到-1表示获取所有值）
     */
    public List<Object> getListByRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list的长度
     */
    public int getListSize(String key) {
        Long size = redisTemplate.opsForList().size(key);
        return size == null ? 0 : size.intValue();
    }

    /**
     * 存list
     *
     * @return 未验证返回的是什么
     */
    public int setList(String key, Object value) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        return count == null ? 0 : count.intValue();
    }

    /**
     * 存list
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 未验证返回的是什么
     */
    public int setList(String key, Object value, long time) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        if (time > 0) {
            setExpire(key, time);
        }
        return count == null ? 0 : count.intValue();
    }

    /**
     * 存list
     *
     * @param key   键
     * @param value 值
     * @return 未验证返回的是什么
     */
    public int setList(String key, List<Object> value) {
        Long count = redisTemplate.opsForList().rightPushAll(key, value);
        return count == null ? 0 : count.intValue();
    }

    /**
     * 存list
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 未验证返回的是什么
     */
    public int setList(String key, List<Object> value, long time) {
        Long count = redisTemplate.opsForList().rightPushAll(key, value);
        if (time > 0) {
            setExpire(key, time);
        }
        return count == null ? 0 : count.intValue();
    }

    /**
     * 存list，根据索引存值
     *
     * @param key   键
     * @param value 值
     * @param index 索引
     */
    public boolean setListByIndex(String key, Object value, long index) {
        redisTemplate.opsForList().set(key, index, value);
        return true;
    }

    /**
     * 删除list中的元素
     *
     * @param key   键
     * @param count 为正数时从左向右删除count个元素，为负数时从右向左开始删除，count为0时删除所有该值元素
     * @param value 值
     * @return 移除的个数
     */
    public int deleteListElement(String key, long count, Object value) {
        Long l = redisTemplate.opsForList().remove(key, count, value);
        return l == null ? 0 : l.intValue();
    }
    /* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ list ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
}