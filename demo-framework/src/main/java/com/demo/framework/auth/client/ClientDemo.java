package com.demo.framework.auth.client;

import com.alibaba.fastjson.JSONObject;

public class ClientDemo {

    public static void main(String[] args) {
        try {
            String clientId = "msvX8ClrjSdmcknj";
            String clientSecret = "QuLab2Uw50Onh1mpSu6ymbISqmMrBf";
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOk0UjdBwQ31sFCQQF8GMAV7GjcHc/WVcbH3nOWYPQS3rf2SWrsb4qEccEaGI0wUTf/yt5czdtlVGOfNVoGo9VnV" +
                    "0MWjyHRsA85M8hKEvgx7vAxFPWxkt+kqE71sJz+Oi1L+8/TBIJpySsBDD0NrERF0+WBqKHTTgIRdWMsAIS7QIDAQAB";

            String baseUrl = "http://127.0.0.1:8080";

            //获取token
            String tokenUrl = baseUrl + "/auth/token";
            String token = HttpClientUtil.getToken(tokenUrl, clientId, clientSecret);

            //请求业务接口
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("hello", "重庆");
            jsonObject.put("hi", "中国");
            String url = baseUrl + "/test/crypt/decryptEncrypt?token=" + token;
            String result = HttpClientUtil.doPost(url, jsonObject.toJSONString(), publicKey);
            System.out.println("响应数据：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}