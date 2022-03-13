package com.qinweizhao.generator.model.result;

public enum SystemResultCode implements ResultCode {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    SERVER_ERROR(500, "Internal Server Error");

    private final Integer code;
    private final String message;

    SystemResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
