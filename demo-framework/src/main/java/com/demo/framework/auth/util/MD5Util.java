package com.demo.framework.auth.util;

import org.springframework.util.DigestUtils;

/**
 * MD5 Util
 *
 * @author 30
 */
public class MD5Util {

    /**
     * 摘要
     */
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}