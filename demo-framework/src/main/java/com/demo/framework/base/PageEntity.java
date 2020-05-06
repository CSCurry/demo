package com.demo.framework.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页对象，需要分页的对象，继承该类
 */
@ApiModel(value = "分页对象")
public class PageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数据", example = "10")
    private Integer pageSize;

    //getter上加@JsonIgnore是为了返回分页数据时屏蔽这两个字段
    //setter上加@JsonProperty是为了请求分页数据时不屏蔽这两个字段

    @JsonIgnore
    public Integer getPageNum() {
        return pageNum;
    }

    @JsonProperty
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @JsonIgnore
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}