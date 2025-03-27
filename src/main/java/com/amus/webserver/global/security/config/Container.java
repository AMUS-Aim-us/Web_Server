package com.amus.webserver.global.security.config;

public interface Container<T> {
    String getResultMsg();

    void setResultMsg(String message);

    T getData();

    void setData(T data);
}
