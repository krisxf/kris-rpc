package com.kris.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Program: kris-rpc
 * @Description: Rpc 返回状态码枚举类
 * @Author: kris
 * @Create: 2025-03-06 21:38
 **/
@AllArgsConstructor
@Getter
@ToString
public enum RpcResponseCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "远程调用成功"),
    /**
     * 失败
     */
    FAIL(500, "远程调用失败");

    /** 状态码 */
    private final int code;

    /** 信息 */
    private final String message;

}
