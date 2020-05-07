package com.demo.web.auth;

import com.alibaba.fastjson.JSON;
import com.demo.framework.auth.access.AccessUser;
import com.demo.framework.auth.access.AccessUserVo;
import com.demo.framework.auth.util.MD5Util;
import com.demo.framework.auth.util.RSAUtil;
import com.demo.framework.base.BaseController;
import com.demo.framework.base.BaseResult;
import com.demo.framework.constant.Constant;
import com.demo.framework.exception.AuthException;
import com.demo.framework.util.RedisUtil;
import com.demo.framework.util.StringUtil;
import com.demo.framework.util.UUIDUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
@RestController
@Api(tags = "鉴权控制器")
@RequestMapping("auth")
public class AuthController extends BaseController {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 创建新的客户端用户
     */
    @PostMapping("newAccessUser")
    public BaseResult newAccessUser() {
        try {
            String clientId = StringUtil.getRandomString(16);
            String clientSecret = StringUtil.getRandomString(30);

            RSAUtil.RSAKeyPair rsaKeyPair = RSAUtil.generateRSAKeyPair();
            String privateKey = rsaKeyPair.getPrivateKey();
            String publicKey = rsaKeyPair.getPublicKey();

            AccessUser accessUser = new AccessUser(clientId, clientSecret, publicKey, privateKey);
            log.info("newAccessUser：{}", JSON.toJSONString(accessUser));

            //clientId:AccessUser存Redis
            boolean b = redisUtil.setString(Constant.REDIS_KEY_PREFIX_ACCESS_USER_CLIENT_ID + clientId, JSON.toJSONString(accessUser));
            return b ? BaseResult.success() : BaseResult.error();
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.error();
        }
    }

    /**
     * 获取token
     */
    @PostMapping("token")
    public BaseResult token(@RequestBody @Validated AccessUserVo vo) {
        String clientId = vo.getClientId();

        //从Redis中获取用户
        AccessUser accessUser = JSON.parseObject(redisUtil.getString(Constant.REDIS_KEY_PREFIX_ACCESS_USER_CLIENT_ID + clientId), AccessUser.class);

        if (accessUser == null) {
            throw new AuthException();
        }

        //服务端签名
        String sign = MD5Util.md5(clientId + accessUser.getClientSecret() + vo.getTimestamp());
        //验证签名
        if (!sign.equals(vo.getSign())) {
            throw new AuthException();
        }

        //生成token，用UUID
        String token = UUIDUtil.getUUID();

        //token:AccessUser存Redis
        redisUtil.setString(Constant.REDIS_KEY_PREFIX_ACCESS_USER_TOKEN + token, JSON.toJSONString(accessUser), Constant.REDIS_KEY_EXPIRE_ACCESS_USER_TOKEN);

        HashMap<Object, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put("token", token);
        return BaseResult.success(map);
    }
}