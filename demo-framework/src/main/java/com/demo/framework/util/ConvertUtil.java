package com.demo.framework.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 转换工具类
 *
 * @author 30
 */
public class ConvertUtil {

    /**
     * String --> Map<String, Object>
     */
    public static Map<String, Object> stringToMap(String str) {
        if (StringUtil.isEmpty(str)) {
            return new HashMap<>();
        }
        return JSONObject.parseObject(str, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * Object --> Map<String, Object>
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return new HashMap<>();
        }
        return JSONObject.parseObject(JSON.toJSONString(obj), new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * Object --> List<Map<String, Object>>
     */
    public static List<Map<String, Object>> objectToList(Object obj) {
        if (obj == null) {
            return new ArrayList<>();
        }
        return JSONObject.parseObject(JSON.toJSONString(obj), new TypeReference<List<Map<String, Object>>>() {
        });
    }
}
