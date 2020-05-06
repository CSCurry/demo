package com.demo.framework.base;

import com.demo.framework.enums.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BaseResult
 *
 * @author 30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult {

    @ApiModelProperty("响应码")
    private String code;
    @ApiModelProperty("响应消息")
    private String message;
    @ApiModelProperty("响应数据")
    private Object data;

    private BaseResult(String code, String message) {
        this.code = code == null ? "" : code;
        this.message = message == null ? "" : message;
    }

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ success ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public static BaseResult success() {
        return success(ResultCode.SUCCESS.getMessage());
    }

    public static BaseResult success(String message) {
        return success(message, null);
    }

    public static BaseResult success(Object data) {
        return success(ResultCode.SUCCESS.getMessage(), data);
    }

    public static BaseResult success(String message, Object data) {
        return new BaseResult(ResultCode.SUCCESS.getCode(), message, data);
    }

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ errorParam ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public static BaseResult errorParam(String message) {
        return new BaseResult(ResultCode.PARAM_ERROR.getCode(), message);
    }

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ error ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public static BaseResult error() {
        return error(ResultCode.ERROR_SYSTEM.getMessage());
    }

    public static BaseResult error(String message) {
        return new BaseResult(ResultCode.ERROR_SYSTEM.getCode(), message);
    }

    public static BaseResult error(ResultCode result) {
        return new BaseResult(result.getCode(), result.getMessage());
    }

}
