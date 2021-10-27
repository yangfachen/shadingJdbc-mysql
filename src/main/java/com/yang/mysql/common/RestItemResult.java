package com.yang.mysql.common;

public class RestItemResult<T> {
    private String code;
    private String msg;
    private T data;

    public RestItemResult(T data){
        this.code = "200";
        this.msg = "";
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}