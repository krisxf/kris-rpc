package com.kris.remote.dto;

import lombok.*;

/**
 * @Program: kris-rpc
 * @Description: Rpc 信息体
 * @Author: kris
 * @Create: 2025-03-16 15:35
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcMessage {
    /**
     * rpc 消息类型
     */
    private byte messageType;
    /**
     * 序列化方式
     */
    private byte codec;
    /**
     * 编码类型
     */
    private byte compress;
    /**
     * 请求 id
     */
    private int requestId;
    /**
     * 数据
     */
    private Object data;
}
