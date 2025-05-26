package com.kris.util;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-05-10 16:10
 **/

public class TraceContext {
    // 使用InheritableThreadLocal支持线程池场景
    private static final ThreadLocal<String> traceHolder = new InheritableThreadLocal<>();

    public static void setTraceId(String traceId) {
        traceHolder.set(traceId);
    }

    public static String getTraceId() {
        return traceHolder.get();
    }

    public static void clear() {
        traceHolder.remove();
    }
}
