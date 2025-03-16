package com.kris.register;

import java.net.InetSocketAddress;

/**
 * @Program: kris-rpc
 * @Description: 服务注册接口
 * @Author: kris
 * @Create: 2025-03-16 15:46
 **/
public interface ServiceRegistry {

    /**
     * 服务注册
     *
     * @param rpcServiceName    服务名称
     * @param inetSocketAddress 服务地址
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);
}
