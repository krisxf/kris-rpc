package com.kris;

/**
 * @Program: kris-rpc
 * @Description: 测试接口
 * @Author: kris
 * @Create: 2025-03-17 21:19
 **/
public interface HelloService {
    /**
     * 说
     * @param greeting 问候词
     * @return 返回
     */
    String sayHello(String greeting);
}
