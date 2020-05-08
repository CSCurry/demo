package com.demo.business.domain;

import com.demo.framework.util.CacheUtil;
import com.demo.framework.constant.Constant;
import com.demo.framework.util.StringUtil;

import java.util.List;

/**
 * 字典工具类
 *
 * @author 30
 */
public class DictUtil {

    /**
     * 设置字典缓存
     *
     * @param key       参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas) {
        CacheUtil.put(getCacheName(), getCacheKey(key), dictDatas);
    }

    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    @SuppressWarnings("unchecked")
    public static List<SysDictData> getDictCache(String key) {
        Object cacheObj = CacheUtil.get(getCacheName(), getCacheKey(key));
        if (StringUtil.isNotNull(cacheObj)) {
            return (List<SysDictData>) cacheObj;
        }
        return null;
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache() {
        CacheUtil.removeAll(getCacheName());
    }

    /**
     * 获取cache name
     *
     * @return 缓存名
     */
    public static String getCacheName() {
        return Constant.SYS_DICT_CACHE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return Constant.SYS_DICT_KEY + configKey;
    }
}
