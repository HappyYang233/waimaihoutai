package com.example.springbootmybatis.entity.UtilEntity;

public class Status {
    int code;
    Object msg;
    public Status(int code, Object desc) {
        this.code = code;
        this.msg = desc;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public Object getMsg() {
        return msg;
    }
}
