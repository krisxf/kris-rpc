package com.kris.util;

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
}
