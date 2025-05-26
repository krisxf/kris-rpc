package com.kris.nettyclient.vo;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-05-11 14:38
 **/

public class ResponseData {
    private int code;
    private Object data;


    public ResponseData(int code, Object items) {
        this.code = code;
        this.data = items;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

