package com.qinweizhao.generator.model.result;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(
        title = "返回体结构"
)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(
            title = "返回状态码",
            defaultValue = "0"
    )
    private int code;
    @Schema(
            title = "返回信息",
            defaultValue = "Success"
    )
    private String message;
    @Schema(
            title = "数据",
            nullable = true,
            defaultValue = "null"
    )
    private T data;

    public R() {
    }

    public R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> R<T> ok() {
        return (R<T>) ok((Object) null);
    }

    public static <T> R<T> ok(T data) {
        return (new R()).setCode(SystemResultCode.SUCCESS.getCode()).setData(data).setMessage(SystemResultCode.SUCCESS.getMessage());
    }

    public static <T> R<T> ok(T data, String message) {
        return (new R()).setCode(SystemResultCode.SUCCESS.getCode()).setData(data).setMessage(message);
    }

    public static <T> R<T> failed(int code, String message) {
        return (new R()).setCode(code).setMessage(message);
    }

    public static <T> R<T> failed(ResultCode failMsg) {
        return (new R()).setCode(failMsg.getCode()).setMessage(failMsg.getMessage());
    }

    public static <T> R<T> failed(ResultCode failMsg, String message) {
        return (new R()).setCode(failMsg.getCode()).setMessage(message);
    }

    public int getCode() {
        return this.code;
    }

    public R<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public R<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "R(code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }
}
