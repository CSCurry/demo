package com.demo.framework.page;

import com.demo.framework.constant.Constant;
import com.demo.framework.util.ServletUtils;

/**
 * 表格数据处理
 *
 * @author ruoyi
 */
public class TableSupport {
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constant.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constant.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constant.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constant.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
