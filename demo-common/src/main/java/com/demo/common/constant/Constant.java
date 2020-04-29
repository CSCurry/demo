package com.demo.common.constant;

public class Constant {

    //Redis - 存AccessUser的key.后面跟clientId
    public static final String REDIS_KEY_PREFIX_ACCESS_USER_CLIENT_ID = "auth_user_client_id_";
    //Redis - 存AccessUser的key.后面跟token
    public static final String REDIS_KEY_PREFIX_ACCESS_USER_TOKEN = "auth_user_token_";
    //Redis - 存AccessUser的key.后面跟token.过期时间(秒)
    public static final long REDIS_KEY_EXPIRE_ACCESS_USER_TOKEN = 360;

    //请求URL路径后面的token参数键
    public static final String REQUEST_URL_PARAMETER_TOKEN = "token";
}
