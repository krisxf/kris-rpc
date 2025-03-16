package com.kris.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Program: kris-rpc
 * @Description: 服务注册枚举类
 * @Author: kris
 * @Create: 2025-03-16 16:12
 **/
@AllArgsConstructor
@Getter
public enum ServiceRegistryEnum {

    /**
     * zookeeper 服务注册
     */
    ZK("zk");

    private final String name;
}
