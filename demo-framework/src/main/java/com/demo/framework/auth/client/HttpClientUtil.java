package com.demo.framework.auth.client;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HttpClientUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * POST
     *
     * @param url   URL
     * @param param 参数
     */
    public static String doPost(String url, JSONObject param) throws Exception {
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result;
        try {
            //创建http请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            //创建请求内容
            StringEntity entity = new StringEntity(param.toString(), Consts.UTF_8);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            log.info("POST请求URL：{}", url);
            log.info("POST请求response：{}", response);
            result = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            log.info("POST请求result：{}", result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("POST请求异常", e);
            throw new Exception();
        } finally {
            //关闭资源
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 生成aesKey
     */
    private static String generatorAesKey() {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * String --> Map<String, Object>
     */
    public static Map<String, Object> stringToMap(String str) {
        if (str == null || str.isEmpty()) {
            return new HashMap<>();
        }
        return JSONObject.parseObject(str, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * 获取token
     *
     * @param url          URL
     * @param clientId     客户端ID
     * @param clientSecret 客户端密钥
     * @return token
     */
    public static String getToken(String url, String clientId, String clientSecret) throws Exception {
        long timestamp = System.currentTimeMillis();
        String sign = MD5ClientUtil.generateMD5(clientId + clientSecret + timestamp);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clientId", clientId);
        jsonObject.put("sign", sign);
        jsonObject.put("timestamp", timestamp);
        String str = doPost(url, jsonObject);
        return String.valueOf(stringToMap(String.valueOf(stringToMap(str).get("data"))).get("token"));
    }

    /**
     * POST
     *
     * @param url            URL
     * @param dataJsonString 业务参数JSON字符串
     * @param publicKey      公钥
     */
    public static String doPost(String url, String dataJsonString, String publicKey) throws Exception {
        //生成aesKey
        String aesKey = generatorAesKey();
        //aesKey加密数据
        String encryptData = AESClientUtil.encrypt(dataJsonString, aesKey);
        //RSA公钥对aesKey进行加密
        String encryptAesKey = RSAClientUtil.encryptByPublicKey(aesKey, publicKey);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", encryptData);
        jsonObject.put("key", encryptAesKey);

        String result = doPost(url, jsonObject);
        if (!result.startsWith("{")) {
            //说明响应数据已加密，用AES解密响应数据
            result = AESClientUtil.decrypt(result, aesKey);
        }
        return result;
    }
}
