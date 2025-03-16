package com.kris.loadbalance;

import com.kris.remote.dto.RpcRequest;
import com.kris.util.CollectionUtil;

import java.util.List;

/**
 * @Program: kris-rpc
 * @Description: 抽象负载均衡类
 * @Author: kris
 * @Create: 2025-03-16 15:07
 **/

public abstract class AbstractLoadBalance implements LoadBalance{

    @Override
    public String selectServiceAddress(List<String> serviceAddresses, RpcRequest rpcRequest) {
        if (CollectionUtil.isEmpty(serviceAddresses)) {
            return null;
        }
        if (serviceAddresses.size() == 1) {
            return serviceAddresses.get(0);
        }
        return doSelect(serviceAddresses, rpcRequest);
    }

    /**
     * 选择地址方法
     * @param serviceAddresses 服务地址列表
     * @param rpcRequest 请求
     * @return 返回选中的地址
     */
    protected abstract String doSelect(List<String> serviceAddresses, RpcRequest rpcRequest);
}
