package com.demo.framework.auth.client;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * AES Util，仅供客户端使用
 */
public class AESClientUtil {

    //默认的加密算法，参数分别代表：算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    private static final String AES = "AES";

    /**
     * 加密
     *
     * @param content    需要加密的字符串
     * @param encryptKey 密钥
     */
    public static String encrypt(String content, String encryptKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), AES));
        byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        // 采用base64算法进行转码，避免出现中文乱码
        return Base64.encodeBase64String(bytes);

    }

    /**
     * 解密
     *
     * @param encryptStr 需要解密的字符串
     * @param decryptKey 密钥
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), AES));
        // 采用base64算法进行转码，避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }
}