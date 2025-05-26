package com.kris.util;

import com.kris.dao.LogService;
import com.kris.dao.entity.Log;
import com.kris.dao.impl.LogServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-05-10 14:43
 **/

public class LogUtil {
    private static final LogService logService = new LogServiceImpl();

    public static void log(String traceId, String content, LocalDateTime time){
        Log log = new Log(traceId,content,time);
        logService.saveLog(log);
    }

    public static List<Log> queryLog(){
        return logService.queryLogByTraceId();
    }
}
