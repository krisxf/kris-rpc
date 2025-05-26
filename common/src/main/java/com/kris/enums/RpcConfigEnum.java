package com.kris.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Program: kris-rpc
 * @Description: Rpc 配置枚举类
 * @Author: kris
 * @Create: 2025-03-16 16:20
 **/
@AllArgsConstructor
@Getter
public enum RpcConfigEnum {

    /** rpc 配置文件路径 */
    RPC_CONFIG_PATH("rpc.properties"),
    /** rpc zookeeper的地址 */
    ZK_ADDRESS("rpc.zookeeper.address"),
    /** rpc port */
    PORT("rpc.port");

    private final String propertyValue;

}
