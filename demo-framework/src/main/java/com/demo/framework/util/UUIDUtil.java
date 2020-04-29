package com.demo.framework.util;

import java.util.UUID;

/**
 * UUIDUtil
 *
 * @author 30
 */
public class UUIDUtil {

    /**
     * 得到一个32位的UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}