package com.example.agricultural2.common;

/**
 * @ClassName: Result
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/30 17:30
 * @Version: 1.0
 **/
public class Result<T> {
    private Integer code;
    private String msg;
    private T Data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
