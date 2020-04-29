package com.demo.framework.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * http工具类
 *
 * @author 30
 */
@Slf4j
public class HttpUtil {

    /**
     * GET
     *
     * @param url URL
     */
    public static String doGet(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            //通过默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            //创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            //httpGet.addHeader("Connection", "keep-alive");
            //设置请求头信息
            httpGet.addHeader("Accept", "application/json");
            //配置请求参数
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000) //设置连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)//设置请求超时时间
                    .setSocketTimeout(60000)//设置数据读取超时时间
                    .build();
            //为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            //执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            //通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            //通过EntityUtils中的toString方法将结果转换为字符串，后续根据需要处理对应的reponse code
            result = EntityUtils.toString(entity);
            log.info("GET请求URL：{}", url);
            log.info("GET请求result：{}", result);
        } catch (Exception e) {
            e.printStackTrace();
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
     * POST
     *
     * @param url   URL
     * @param param 参数
     */
    public static String doPost(String url, Map<String, Object> param) throws Exception {
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result;
        try {
            //创建http请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            //创建请求内容
            JSONObject jsonObjectParam = JSONObject.parseObject(JSON.toJSONString(param));
            StringEntity entity = new StringEntity(jsonObjectParam.toString(), Consts.UTF_8);
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
     * POST
     *
     * @param url  URL
     * @param body body内容
     */
    public static String doPost(String url, String body) throws Exception {
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result;
        try {
            //创建http请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            //创建请求内容
            httpPost.setEntity(new StringEntity(body, Consts.UTF_8));
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
}