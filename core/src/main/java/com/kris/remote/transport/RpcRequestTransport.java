package com.kris.remote.transport;

import com.kris.extension.SPI;
import com.kris.remote.dto.RpcRequest;

/**
 * @Program: kris-rpc
 * @Description: 请求传输接口
 * @Author: kris
 * @Create: 2025-03-16 15:38
 **/
@SPI
public interface RpcRequestTransport {
    /**
     * 向服务器发送rpc请求并获取结果
     *
     * @param rpcRequest 消息体
     * @return 来自服务器的数据
     */
    Object sendRpcRequest(RpcRequest rpcRequest);
}
