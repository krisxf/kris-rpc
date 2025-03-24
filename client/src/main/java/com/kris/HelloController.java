package com.kris;

import com.kris.annotation.RpcConsumer;
import org.springframework.stereotype.Component;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-23 14:37
 **/

@Component
public class HelloController {

    @RpcConsumer
    private HelloService helloService;

    public void test() throws InterruptedException {
        String hello = this.helloService.sayHello("greetings!!!");
        Thread.sleep(12000);
        for (int i = 0; i < 3; i++) {
            System.out.println(helloService.sayHello("greetings!!!"));
        }
    }


}
