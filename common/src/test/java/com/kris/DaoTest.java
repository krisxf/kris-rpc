package com.kris;

import com.kris.dao.entity.Log;
import com.kris.dao.impl.LogServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-05-10 14:10
 **/

public class DaoTest {
    public static void main(String[] args) {
        Log log = new Log();
        log.setTraceId("1234");
        log.setContent("测试日志");
        log.setTime(LocalDateTime.now());

        LogServiceImpl logService = new LogServiceImpl();
//        logService.saveLog(log);

        List<Log> logs = logService.queryLogByTraceId();
        logs.forEach(System.out::println);
    }
}
