package com.kris.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Program: kris-rpc
 * @Description: 服务发现枚举类
 * @Author: kris
 * @Create: 2025-03-16 15:54
 **/
@AllArgsConstructor
@Getter
public enum ServiceDiscoveryEnum {
    /** zookeeper */
    ZK("zk");

    private final String name;
}
