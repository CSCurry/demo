package com.demo.framework.taxno.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.framework.auth.util.MD5Util;
import com.demo.framework.taxno.TaxNoService;
import com.demo.framework.taxno.domain.KingdeeResult;
import com.demo.framework.util.ConvertUtil;
import com.demo.framework.util.HttpUtil;
import com.demo.framework.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TaxNoServiceImpl implements TaxNoService {

    @Value("${jd.baseUrl}")
    private String baseUrl;
    @Value("${jd.client_id}")
    private String clientId;
    @Value("${jd.client_secret}")
    private String clientSecret;

    /**
     * 获取token
     */
    private String getToken() throws Exception {
        String url = baseUrl + "/base/oauth/token";
        long timestamp = System.currentTimeMillis();
        String sign = MD5Util.generateMD5(clientId + clientSecret + timestamp);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_id", clientId);
        jsonObject.put("sign", sign);
        jsonObject.put("timestamp", timestamp);

        String str = HttpUtil.doPost(url, jsonObject);
        if (StringUtil.isEmpty(str)) {
            return StringUtil.EMPTY;
        }
        KingdeeResult result = JSON.parseObject(str, KingdeeResult.class);
        if (!KingdeeResult.ErrorCode.SUCCESS.getCode().equals(result.getErrcode())) {
            return StringUtil.EMPTY;
        }
        return result.getAccess_token();
    }

    /**
     * 根据企业名称查税号 or 根据税号查企业名称
     */
    @Override
    public List<Map<String, Object>> companyInfo(String content) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            String token = getToken();
            if (StringUtil.isEmpty(token)) {
                return list;
            }

            String url = baseUrl + "/bill/company/info/query?access_token=" + token + "&name=" + content;
            String str = HttpUtil.doGet(url);

            if (StringUtil.isEmpty(str)) {
                return list;
            }
            KingdeeResult result = JSON.parseObject(str, KingdeeResult.class);
            if (!KingdeeResult.Code.SUCCESS.getCode().equals(result.getCode())) {
                return list;
            }
            return ConvertUtil.objectToList(result.getDatas());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}