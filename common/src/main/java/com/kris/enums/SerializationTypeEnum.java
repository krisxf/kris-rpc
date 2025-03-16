package com.kris.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Program: kris-rpc
 * @Description: 序列化方式枚举类
 * @Author: kris
 * @Create: 2025-03-16 15:55
 **/
@AllArgsConstructor
@Getter
public enum SerializationTypeEnum {
    /** kyro 序列化方式 */
    KYRO((byte) 0x01, "kyro"),
    /** protostuff 序列化方式 */
    PROTOSTUFF((byte) 0x02, "protostuff"),
    /** hessian 序列化方式 */
    HESSIAN((byte) 0X03, "hessian");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (SerializationTypeEnum c : SerializationTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

}
