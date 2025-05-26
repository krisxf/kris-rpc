package com.kris.dao;

import com.kris.dao.entity.Log;

import java.util.List;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-05-10 14:00
 **/
public interface LogService {
    /**
     * 保存日志
     * @param log 日志对象
     * @return xx
     */
    int saveLog(Log log);

    /**
     * 根据traceId 查询日志
     * @return 返回日志列表
     */
    List<Log> queryLogByTraceId();
}
