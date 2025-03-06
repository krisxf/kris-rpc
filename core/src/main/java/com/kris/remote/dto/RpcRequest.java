package com.kris.remote.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @Program: kris-rpc
 * @Description: Rpc 请求类
 * @Author: kris
 * @Create: 2025-03-06 21:32
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;

    public String getRpcServiceName() {
        return this.getInterfaceName() + this.getGroup() + this.getVersion();
    }
}
