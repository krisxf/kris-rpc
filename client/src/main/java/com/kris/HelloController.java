package com.kris;

import com.kris.annotation.RpcConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-23 14:37
 **/

@Component
@Slf4j
public class HelloController {
    @RpcConsumer
    private HelloService helloService;

    public void test() throws InterruptedException {
        String hello = this.helloService.sayHello("hello");
        log.info("调用结果返回：{}",hello);
    }


}
