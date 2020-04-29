package com.demo.framework.auth.access;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 访问用户VO
 *
 * @author 30
 */
@Data
public class AccessUserVo {

    @NotEmpty(message = "clientId不能为空")
    @ApiModelProperty(value = "客户端用户ID")
    private String clientId;

    @NotEmpty(message = "sign不能为空")
    @ApiModelProperty(value = "签名")
    private String sign;

    @NotEmpty(message = "timestamp不能为空")
    @ApiModelProperty(value = "时间戳")
    private String timestamp;

}