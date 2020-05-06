package com.demo.framework.base;

import com.demo.framework.enums.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BaseResultVO
 *
 * @author 30
 */
@SuppressWarnings({"unchecked"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResultVO<T> {

    @ApiModelProperty("响应码")
    private String code;
    @ApiModelProperty("响应消息")
    private String message;
    @ApiModelProperty("响应数据")
    private T data;

    private BaseResultVO(String code, String message) {
        this.code = code == null ? "" : code;
        this.message = message == null ? "" : message;
    }

    public static <T> T success(Object data) {
        return (T) new BaseResultVO<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> T error() {
        return (T) new BaseResultVO<>(ResultCode.ERROR_SYSTEM.getCode(), ResultCode.ERROR_SYSTEM.getMessage());
    }
}
