package com.demo.framework.taxno;

import java.util.List;
import java.util.Map;

public interface TaxNoService {

    /**
     * 根据企业名称查税号 or 根据税号查企业名称
     *
     * @param content 企业名称 or 税号
     * @return 查询结果集
     */
    List<Map<String, Object>> companyInfo(String content);
}