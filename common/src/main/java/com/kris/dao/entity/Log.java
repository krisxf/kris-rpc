package com.kris.dao.entity;

import java.time.LocalDateTime;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-05-10 14:01
 **/

public class Log {
    private int id;
    private String traceId;
    private String content;
    private LocalDateTime time;

    public Log() {
    }

    public Log(String traceId, String content, LocalDateTime time) {
        this.traceId = traceId;
        this.content = content;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", traceId='" + traceId + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
