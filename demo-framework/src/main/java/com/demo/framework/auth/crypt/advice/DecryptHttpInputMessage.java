package com.demo.framework.auth.crypt.advice;

import com.alibaba.fastjson.JSON;
import com.demo.common.constant.Constant;
import com.demo.framework.auth.access.AccessUser;
import com.demo.framework.auth.crypt.annotation.Decrypt;
import com.demo.framework.auth.util.AESUtil;
import com.demo.framework.auth.util.RSAUtil;
import com.demo.framework.exception.DecryptException;
import com.demo.framework.exception.ParamEmptyException;
import com.demo.framework.util.ConvertUtil;
import com.demo.framework.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DecryptHttpInputMessage
 *
 * @author 30
 */
@Slf4j
public class DecryptHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;
    private InputStream body;

    public DecryptHttpInputMessage(HttpInputMessage inputMessage, MethodParameter parameter, RedisUtil redisUtil) {
        String data;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

            this.headers = inputMessage.getHeaders();
            String content = new BufferedReader(new InputStreamReader(inputMessage.getBody())).lines().collect(Collectors.joining(System.lineSeparator()));

            Map<String, Object> contentMap = ConvertUtil.stringToMap(content);
            //aesKey经RSA公钥加密后的数据
            String encryptAesKey = String.valueOf(contentMap.get("key"));
            //data经AES加密后的数据
            String encryptData = String.valueOf(contentMap.get("data"));

            //用RSA私钥解密aesKey
            String[] values = request.getParameterMap().get(Constant.REQUEST_URL_PARAMETER_TOKEN);
            String token = values[0];
            AccessUser accessUser = JSON.parseObject(redisUtil.getString(Constant.REDIS_KEY_PREFIX_ACCESS_USER_TOKEN + token), AccessUser.class);
            String privateKey = accessUser.getPrivateKey();
            String aesKey = RSAUtil.decryptByPrivateKey(encryptAesKey, privateKey);

            //用AES解密data
            data = AESUtil.decrypt(encryptData, aesKey);

            request.setAttribute("aesKey", aesKey);

            this.body = new ByteArrayInputStream(data.getBytes());

            log.info("接口：{}", request.getRequestURI());
            log.info("请求数据解密前：{}", content);
            log.info("当前request域aesKey：{}", aesKey);
            log.info("请求数据解密后：{}", data);
        } catch (Exception e) {
            log.error("解密异常", e);
            throw new DecryptException(e.getMessage());
        }

        if (data.isEmpty()) {
            //判断是否不能为null或空串
            boolean notEmpty = Objects.requireNonNull(parameter.getMethod()).getAnnotation(Decrypt.class).notEmpty();
            if (notEmpty) {
                throw new ParamEmptyException();
            }
        }
    }

    @Override
    public InputStream getBody() {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}