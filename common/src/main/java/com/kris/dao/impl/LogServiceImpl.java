package com.kris.dao.impl;

import com.kris.dao.BaseDAO;
import com.kris.dao.LogService;
import com.kris.dao.entity.Log;

import java.util.List;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-05-10 14:02
 **/

public class LogServiceImpl extends BaseDAO<Log> implements LogService {
    @Override
    public int saveLog(Log log) {
        return super.executeUpdate("insert into tb_log values(?,?,?,?)",0,log.getTraceId(),log.getContent(),log.getTime());
    }

    @Override
    public List<Log> queryLogByTraceId() {
        return super.executeQuery("select * from tb_log order by id desc limit 12");
    }

}
