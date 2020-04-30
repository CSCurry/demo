package com.demo.business.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "test对象")
public class TestEntity {

    @NotNull(message = "商品名称不能为空")
    @ApiModelProperty(value = "商品名称", example = "香蕉")
    private String productName;

    @ApiModelProperty(value = "商品价格", example = "9.9")
    private Double productPrice;

    @DecimalMin("20")
    @ApiModelProperty(value = "商品库存", example = "999")
    private Integer productStock;
}