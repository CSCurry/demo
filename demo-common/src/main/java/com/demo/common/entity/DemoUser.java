package com.demo.common.entity;

import com.demo.common.base.PageEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * demo
 */
@Getter
@Setter
@ApiModel(value = "demo对象")
public class DemoUser extends PageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名", example = "阿辉")
    private String name;

    @ApiModelProperty(value = "年龄", example = "23")
    private Integer age;

    @ApiModelProperty(value = "身高", example = "168.5")
    private Double height;

}