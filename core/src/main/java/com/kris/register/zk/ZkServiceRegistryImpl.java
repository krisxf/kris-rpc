package com.kris.register.zk;

import com.kris.register.ServiceRegistry;
import com.kris.util.CuratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-17 21:44
 **/

@Slf4j
public class ZkServiceRegistryImpl implements ServiceRegistry {

    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        String servicePath = CuratorUtil.ZK_REGISTER_ROOT_PATH + "/" + rpcServiceName + inetSocketAddress.toString();
        CuratorFramework zkClient = CuratorUtil.getZkClient();
        CuratorUtil.createPersistentNode(zkClient, servicePath);
    }
}

