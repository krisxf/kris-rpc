package com.kris.remote.transport;

import com.kris.remote.dto.RpcRequest;

/**
 * @Program: kris-rpc
 * @Description: 请求传输接口
 * @Author: kris
 * @Create: 2025-03-16 15:38
 **/
public interface RpcRequestTransport {
    /**
     * send rpc request to server and get result
     *
     * @param rpcRequest message body
     * @return data from server
     */
    Object sendRpcRequest(RpcRequest rpcRequest);
}
