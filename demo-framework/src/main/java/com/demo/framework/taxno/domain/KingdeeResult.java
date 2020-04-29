package com.demo.framework.taxno.domain;

import lombok.Data;

/**
 * 金蝶接口返回结果
 *
 * @author 30
 */
@Data
public class KingdeeResult {

    private String errcode;
    private String code;

    private Object data;
    private Object datas;

    private String access_token;
    private String token_type;
    private Long expires_in;
    private String description;

    /**
     * 状态码枚举
     */
    public enum ErrorCode {
        SUCCESS("0000", "成功");
        private final String code;
        private final String info;

        private ErrorCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    public enum Code {
        SUCCESS("200", "成功");
        private final String code;
        private final String info;

        private Code(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }
}