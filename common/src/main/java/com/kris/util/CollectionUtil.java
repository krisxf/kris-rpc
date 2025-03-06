package com.kris.util;

import java.util.Collection;

/**
 * @Program: kris-rpc
 * @Description: 集合工具类
 * @Author: kris
 * @Create: 2025-03-06 20:57
 **/

public class CollectionUtil {
    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }
}
