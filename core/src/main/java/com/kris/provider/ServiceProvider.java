package com.kris.provider;

import com.kris.config.RpcServiceConfig;

/**
 * @Program: kris-rpc
 * @Description: 服务提供接口类
 * @Author: kris
 * @Create: 2025-03-16 16:00
 **/
public interface ServiceProvider {

    /**
     * 添加服务
     * @param rpcServiceConfig rpc 服务相关配置
     */
    void addService(RpcServiceConfig rpcServiceConfig);

    /**
     * 获取服务
     * @param rpcServiceName rpc 服务名称
     * @return 提供的服务对象
     */
    Object getService(String rpcServiceName);

    /**
     * 发布服务
     * @param rpcServiceConfig rpc 服务相关配置
     */
    void publishService(RpcServiceConfig rpcServiceConfig);

}
