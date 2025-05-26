package com.kris.impl;

import com.kris.HelloService;
import com.kris.annotation.RpcProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-17 21:22
 **/

@Slf4j
@RpcProvider
public class HelloServiceImpl implements HelloService {

    static {
        System.out.println("HelloServiceImpl被创建");
    }

    @Override
    public String sayHello(String greeting) {
        log.info("收到的问候词为 ： {} + time : {}",greeting,new Date());
        return greeting;
    }
}
