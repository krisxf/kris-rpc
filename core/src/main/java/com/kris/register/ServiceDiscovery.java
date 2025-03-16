package com.kris.register;

import com.kris.remote.dto.RpcRequest;

import java.net.InetSocketAddress;

/**
 * @Program: kris-rpc
 * @Description: 服务发现接口
 * @Author: kris
 * @Create: 2025-03-16 15:45
 **/

public interface ServiceDiscovery {
    /**
     * 查询服务
     *
     * @param rpcRequest 服务查询请求
     * @return 服务地址
     */
    InetSocketAddress lookupService(RpcRequest rpcRequest);
}
