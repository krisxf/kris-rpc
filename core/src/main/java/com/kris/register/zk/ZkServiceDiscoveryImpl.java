package com.kris.register.zk;

import com.kris.enums.LoadBalanceEnum;
import com.kris.enums.RpcErrorMessageEnum;
import com.kris.exception.RpcException;
import com.kris.extension.ExtensionLoader;
import com.kris.loadbalance.LoadBalance;
import com.kris.register.ServiceDiscovery;
import com.kris.remote.dto.RpcRequest;
import com.kris.util.CollectionUtil;
import com.kris.util.CuratorUtil;
import com.kris.util.LogUtil;
import com.kris.util.TraceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-17 21:41
 **/

@Slf4j
public class ZkServiceDiscoveryImpl implements ServiceDiscovery {
    private final LoadBalance loadBalance;

    public ZkServiceDiscoveryImpl() {
        this.loadBalance = ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension(LoadBalanceEnum.LOADBALANCE.getName());
    }

    @Override
    public InetSocketAddress lookupService(RpcRequest rpcRequest) {
        String rpcServiceName = rpcRequest.getRpcServiceName();
        CuratorFramework zkClient = CuratorUtil.getZkClient();
        List<String> serviceUrlList = CuratorUtil.getChildrenNodes(zkClient, rpcServiceName);
        if (CollectionUtil.isEmpty(serviceUrlList)) {
            throw new RpcException(RpcErrorMessageEnum.SERVICE_CAN_NOT_BE_FOUND, rpcServiceName);
        }
        // 负载均衡
        if(!serviceUrlList.isEmpty()){
            LogUtil.log(TraceContext.getTraceId(),"找到服务地址", LocalDateTime.now());
        }
        String targetServiceUrl = loadBalance.selectServiceAddress(serviceUrlList, rpcRequest);
        log.info("成功找到服务地址:[{}]", targetServiceUrl);
        LogUtil.log(TraceContext.getTraceId(),"根据负载均衡策略选取服务地址: "+ targetServiceUrl, LocalDateTime.now());
        String[] socketAddressArray = targetServiceUrl.split(":");
        String host = socketAddressArray[0];
        int port = Integer.parseInt(socketAddressArray[1]);
        return new InetSocketAddress(host, port);
    }
}
