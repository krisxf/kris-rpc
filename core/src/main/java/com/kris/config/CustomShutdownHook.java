package com.kris.config;

import com.kris.remote.transport.netty.server.NettyRpcServer;
import com.kris.util.CuratorUtil;
import com.kris.util.ThreadPoolFactoryUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @Program: kris-rpc
 * @Description: 当服务器关闭时，执行诸如注销所有服务之类的操作
 * @Author: kris
 * @Create: 2025-03-16 16:06
 **/

@Slf4j
public class CustomShutdownHook {
    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK = new CustomShutdownHook();

    public static CustomShutdownHook getCustomShutdownHook() {
        return CUSTOM_SHUTDOWN_HOOK;
    }

    public void clearAll() {
        log.info("addShutdownHook for clearAll");
        // 注册一个关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                // 获取本机 IP
                InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), NettyRpcServer.PORT);
                // 从 Zookeeper 注册该服务
                CuratorUtil.clearRegistry(CuratorUtil.getZkClient(), inetSocketAddress);
            } catch (UnknownHostException ignored) {
            }
            ThreadPoolFactoryUtil.shutDownAllThreadPool();
        }));
    }
}
