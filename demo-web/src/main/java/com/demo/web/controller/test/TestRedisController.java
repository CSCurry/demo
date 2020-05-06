package com.demo.web.controller.test;

import com.demo.framework.base.BaseController;
import com.demo.framework.base.BaseResult;
import com.demo.framework.util.RedisUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
@RestController
@Api(tags = "测试控制器 - Redis")
@RequestMapping("test/redis")
public class TestRedisController extends BaseController {

    @Resource
    RedisUtil redisUtil;

    @PostMapping("setString")
    public BaseResult setString() {
        redisUtil.setString("hello", "world");
        redisUtil.setString("testExpire", "测试过期，设置60s", 60);
        return BaseResult.success();
    }

    @PostMapping("getString")
    public BaseResult getString() {
        String hello = redisUtil.getString("hello");
        String testExpire = redisUtil.getString("testExpire");
        HashMap<Object, Object> map = Maps.newHashMapWithExpectedSize(2);
        map.put("hello", hello);
        map.put("testExpire", testExpire);
        return BaseResult.success(map);
    }

    @PostMapping("setExpire")
    public BaseResult setExpire() {
        Boolean result = redisUtil.setExpire("testExpire", 30);
        return BaseResult.success(result);
    }

    @PostMapping("getExpire")
    public BaseResult getExpire() {
        Long expire = redisUtil.getExpire("testExpire");
        return BaseResult.success(expire);
    }

    @PostMapping("increment")
    public BaseResult increment() {
        Long i = redisUtil.increment("hello", 3);
        return BaseResult.success(i);
    }

    @PostMapping("decrement")
    public BaseResult decrement() {
        Long i = redisUtil.decrement("j", 1);
        return BaseResult.success(i);
    }

    @PostMapping("deleteMany")
    public BaseResult deleteMany() {
        int num = redisUtil.delete("i", "j");
        return BaseResult.success(num);
    }

    @PostMapping("deleteOne")
    public BaseResult deleteOne() {
        Boolean result = redisUtil.delete("hello");
        return BaseResult.success(result);
    }
}