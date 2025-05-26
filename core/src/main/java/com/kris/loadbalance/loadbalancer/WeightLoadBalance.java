package com.kris.loadbalance.loadbalancer;

import com.kris.loadbalance.AbstractLoadBalance;
import com.kris.remote.dto.RpcRequest;

import java.util.List;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-04-21 17:29
 **/

public class WeightLoadBalance extends AbstractLoadBalance {

    @Override
    protected String doSelect(List<String> serviceAddresses, RpcRequest rpcRequest) {
        return null;
    }
}
