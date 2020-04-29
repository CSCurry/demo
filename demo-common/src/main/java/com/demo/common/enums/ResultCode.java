package com.demo.common.enums;

import lombok.Getter;

@Getter
public enum ResultCode {

    /* 成功 */
    SUCCESS("1", "成功"),

    /* 权限错误：1001-1999 */
    ACCESS_DENIED("1001", "无访问权限"),

    /* 数据安全错误：2001-2999 */
    DATA_DECRYPT_ERROR("2001", "数据安全校验失败"),
    DATA_ENCRYPT_ERROR("2002", "数据安全加密失败"),

    /* 参数错误：3000-3999 */
    PARAM_ERROR("3000", "参数错误"),
    PARAM_MISSING("3001", "参数缺失"),
    PARAM_EMPTY("3002", "参数为空"),
    PARAM_TYPE_ERROR("3003", "参数类型错误"),

    /* 系统数据错误：4001-4999 */
    DATA_NOT_FOUND("4001", "系统数据未找到"),
    DATA_IS_ERROR("4002", "系统数据错误"),

    /* 重复提交请求 */
    REPEAT_SUBMIT("8000", "请勿重复提交请求"),

    /* 系统异常 */
    ERROR_SYSTEM("9000", "系统错误，请联系管理员");

    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}