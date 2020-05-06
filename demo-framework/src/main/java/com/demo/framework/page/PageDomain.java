package com.demo.framework.page;

import com.demo.framework.constant.StringUtils;
import lombok.Data;

/**
 * 分页数据
 *
 * @author 30
 */
@Data
public class PageDomain {

    /**
     * 当前记录起始索引
     */
    private Integer pageNum;
    /**
     * 每页显示记录数
     */
    private Integer pageSize;
    /**
     * 排序列
     */
    private String orderByColumn;
    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    private String isAsc;

    public String getOrderBy() {
        if (orderByColumn == null || orderByColumn.length() == 0) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }
}