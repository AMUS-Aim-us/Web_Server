package com.amus.webserver.global.security.config;

/**
 * Dto를 배달하기 위한 클래스.<br>data와 함께 넣을 메세지를 포함한다.
 * @param <T> Dto에 들어가 데이터 타입
 */
public class DtoContainer<T> implements Container<T> {
    private String msg;
    private T data;

    public DtoContainer(){}

    public DtoContainer(String message, T data){
        msg = message;
        this.data = data;
    }

    @Override
    public String getResultMsg() {
        return msg;
    }

    @Override
    public void setResultMsg(String message) {
        msg = message;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }
}
