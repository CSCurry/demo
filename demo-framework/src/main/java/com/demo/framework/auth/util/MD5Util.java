package com.demo.framework.auth.util;

import org.springframework.util.DigestUtils;

/**
 * MD5 Util
 *
 * @author 30
 */
public class MD5Util {

    public static String generateMD5(String originStr) {
        return DigestUtils.md5DigestAsHex(originStr.getBytes());
    }
}