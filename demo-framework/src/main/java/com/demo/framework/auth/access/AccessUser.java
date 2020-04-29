package com.demo.framework.auth.access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 授权访问用户
 *
 * @author 30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessUser {

    //客户端用户ID，长度16
    private String clientId;

    //客户端用户秘钥，长度30
    private String clientSecret;

    //RSA公钥
    private String publicKey;

    //RSA私钥
    private String privateKey;

}