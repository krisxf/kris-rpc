package com.kris.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Program: kris-rpc
 * @Description: 压缩方式枚举类
 * @Author: kris
 * @Create: 2025-03-16 15:55
 **/
@AllArgsConstructor
@Getter
public enum CompressTypeEnum {

    /** gzip 压缩方式 */
    GZIP((byte) 0x01, "gzip");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (CompressTypeEnum c : CompressTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

}