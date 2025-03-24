package com.kris.spring;

import com.kris.annotation.RpcConsumer;
import com.kris.annotation.RpcProvider;
import com.kris.config.RpcServiceConfig;
import com.kris.factory.SingletonFactory;
import com.kris.provider.ServiceProvider;
import com.kris.provider.impl.ZkServiceProviderImpl;
import com.kris.proxy.RpcClientProxy;
import com.kris.remote.transport.RpcRequestTransport;
import com.kris.remote.transport.netty.client.NettyRpcClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @Program: kris-rpc
 * @Description: 在创建bean之前调用此方法，查看类是否已注释
 * @Author: kris
 * @Create: 2025-03-23 15:50
 **/

@Slf4j
@Component
public class SpringBeanPostProcessor implements BeanPostProcessor {

    private final ServiceProvider serviceProvider;
    private final RpcRequestTransport rpcClient;

    public SpringBeanPostProcessor() {
        this.serviceProvider = SingletonFactory.getInstance(ZkServiceProviderImpl.class);
        this.rpcClient = new NettyRpcClient();
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RpcProvider.class)) {
            log.info("[{}] 被注解 [{}] 标注", bean.getClass().getName(), RpcProvider.class.getCanonicalName());
            // 获取RpcService注释
            RpcProvider rpcService = bean.getClass().getAnnotation(RpcProvider.class);
            // 构建RpcServiceProperties
            RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                    .group(rpcService.group())
                    .version(rpcService.version())
                    .service(bean).build();
            serviceProvider.publishService(rpcServiceConfig);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            RpcConsumer rpcReference = declaredField.getAnnotation(RpcConsumer.class);
            if (rpcReference != null) {
                RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                        .group(rpcReference.group())
                        .version(rpcReference.version()).build();
                RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient, rpcServiceConfig);
                Object clientProxy = rpcClientProxy.getProxy(declaredField.getType());
                declaredField.setAccessible(true);
                try {
                    declaredField.set(bean, clientProxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return bean;
    }
}
