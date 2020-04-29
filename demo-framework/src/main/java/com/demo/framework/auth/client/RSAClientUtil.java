package com.demo.framework.auth.client;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA Util，仅供客户端使用
 */
public class RSAClientUtil {

    /**
     * encryption algorithm RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 使用RSA公钥加密数据
     *
     * @param data      需要加密的数据
     * @param publicKey RSA公钥
     */
    public static String encryptByPublicKey(String data, String publicKey) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        byte[] result = cipher.doFinal(data.getBytes());
        return Base64.encodeBase64String(result);
    }
}