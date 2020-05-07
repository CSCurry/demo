package com.demo.framework.page;

import com.demo.framework.constant.Constant;
import com.demo.framework.util.ServletUtil;

/**
 * 表格数据处理
 *
 * @author 30
 */
public class TableSupport {

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtil.getParameterToInt(Constant.PAGE_NUM));
        pageDomain.setPageSize(ServletUtil.getParameterToInt(Constant.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtil.getParameter(Constant.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtil.getParameter(Constant.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
