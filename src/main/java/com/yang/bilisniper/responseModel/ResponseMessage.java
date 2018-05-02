package com.yang.bilisniper.responseModel;

public class ResponseMessage<T> {
    private int code;

    private String message;

    protected T data;

    public ResponseMessage() {
        this.code = 0;
        this.message = "operation succeed";
    }

    public ResponseMessage(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" + "code=" + code + ", message='" + message
                + '\'' + ", data=" + data + '}';
    }
}
