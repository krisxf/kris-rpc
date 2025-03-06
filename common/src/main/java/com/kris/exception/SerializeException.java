package com.kris.exception;

/**
 * @Program: kris-rpc
 * @Description: 序列化异常类
 * @Author: kris
 * @Create: 2025-03-06 21:28
 **/

public class SerializeException extends RuntimeException {
    public SerializeException(String message) {
        super(message);
    }
}
