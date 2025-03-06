package com.kris.config;

import lombok.*;

/**
 * @Program: kris-rpc
 * @Description: Rpc 服务配置类
 * @Author: kris
 * @Create: 2025-03-06 21:05
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcServiceConfig {
    /**
     * 服务版本
     */
    private String version = "";
    /**
     * 当一个服务有多个实现的时候，使用 group 来区分它们
     */
    private String group = "";

    /**
     * 目标服务
     */
    private Object service;

    public String getRpcServiceName() {
        return this.getServiceName() + this.getGroup() + this.getVersion();
    }

    public String getServiceName() {
        return this.service.getClass().getInterfaces()[0].getCanonicalName();
    }
}
