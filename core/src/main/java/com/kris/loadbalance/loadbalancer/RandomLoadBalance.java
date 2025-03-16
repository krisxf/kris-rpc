package com.kris.loadbalance.loadbalancer;

import com.kris.loadbalance.AbstractLoadBalance;
import com.kris.remote.dto.RpcRequest;

import java.util.List;
import java.util.Random;

/**
 * @Program: kris-rpc
 * @Description: 随机算法
 * @Author: kris
 * @Create: 2025-03-16 15:10
 **/

public class RandomLoadBalance extends AbstractLoadBalance {
    @Override
    protected String doSelect(List<String> serviceAddresses, RpcRequest rpcRequest) {
        Random random = new Random();
        return serviceAddresses.get(random.nextInt(serviceAddresses.size()));
    }
}
