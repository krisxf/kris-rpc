package com.kris.exception;

import com.kris.enums.RpcErrorMessageEnum;

/**
 * @Program: kris-rpc
 * @Description: Rpc 自定义异常
 * @Author: kris
 * @Create: 2025-03-16 15:48
 **/

public class RpcException extends RuntimeException {
    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum, String detail) {
        super(rpcErrorMessageEnum.getMessage() + ":" + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum) {
        super(rpcErrorMessageEnum.getMessage());
    }
}

