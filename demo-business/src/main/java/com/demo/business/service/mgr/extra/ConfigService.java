package com.demo.business.service.mgr.extra;

import com.demo.business.service.mgr.ISysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * html调用 thymeleaf 实现参数管理
 *
 * @author 30
 */
@Service("config")
public class ConfigService {

    @Resource
    private ISysConfigService configService;

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    public String getKey(String configKey) {
        return configService.selectConfigByKey(configKey);
    }
}