package com.kris.util;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Program: kris-rpc
 * @Description: 字符串工具类
 * @Author: kris
 * @Create: 2025-03-16 15:52
 **/

public class StringUtil {
    public static boolean isBlank(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 生成格式：13位时间戳 + 4位随机十六进制数（总长度17）
     */
    public static String generateTraceId() {
        // 获取当前时间戳（13位）
        String timestamp = String.valueOf(Instant.now().toEpochMilli());

        // 生成4位随机十六进制数（0x0000~0xFFFF）
        String randomHex = String.format("%04X", ThreadLocalRandom.current().nextInt(0x10000));

        return timestamp + randomHex;
    }
}
