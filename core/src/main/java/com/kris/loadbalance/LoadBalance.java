package com.kris.loadbalance;

import com.kris.extension.SPI;
import com.kris.remote.dto.RpcRequest;

import java.util.List;

/**
 * @Program: kris-rpc
 * @Description: 负载均衡接口
 * @Author: kris
 * @Create: 2025-03-16 15:06
 **/
@SPI
public interface LoadBalance {
    /**
     * 从存在服务接口中选择一个
     *
     * @param serviceUrlList 服务地址列表
     * @param rpcRequest  请求
     * @return 选中的目标地址
     */
    String selectServiceAddress(List<String> serviceUrlList, RpcRequest rpcRequest);
}
