package com.kris.nettyclient.controller;

import com.kris.HelloService;
import com.kris.annotation.RpcConsumer;
import com.kris.dao.entity.Log;
import com.kris.nettyclient.vo.ResponseData;
import com.kris.nettyclient.vo.ServiceInfo;
import com.kris.util.LogUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-23 14:37
 **/

@CrossOrigin(origins = "*")
@RestController
public class HelloController {
    @RpcConsumer
    private HelloService helloService;

    @GetMapping("/")
    public String test() throws InterruptedException {

        return "hello";
    }

    @GetMapping("/getList")
    public ResponseData getList() throws InterruptedException {
        List<ServiceInfo> list = new ArrayList<>();
        list.add(new ServiceInfo(1,"com.kris.helloService","192.168.128.1"));
        return new ResponseData(20000, list);
    }

    @GetMapping("/do")
    public ResponseData doMethod(){
        String hello = helloService.sayHello("hello");
//        String hello = "hello";
        return new ResponseData(20000,hello);
    }

    @GetMapping("/getLog")
    public ResponseData getLog(){
        List<Log> logs = LogUtil.queryLog();
        Collections.reverse(logs);
        return new ResponseData(20000,logs);
    }

    @GetMapping("/getAllLog")
    public ResponseData getAllLog() throws IOException {
        String fileName = "D:\\Coding\\project\\kris-rpc\\logFile.2025-05-12_14-43.log";
        String fileContent = Files.readString(Paths.get(fileName));
        return new ResponseData(20000,fileContent);
    }



}
