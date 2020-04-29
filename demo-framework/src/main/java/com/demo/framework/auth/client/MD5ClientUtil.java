package com.demo.framework.auth.client;

import org.springframework.util.DigestUtils;

/**
 * MD5 Util，仅供客户端使用
 *
 * @author 30
 */
public class MD5ClientUtil {

    public static String generateMD5(String originStr) {
        return DigestUtils.md5DigestAsHex(originStr.getBytes());
    }
}