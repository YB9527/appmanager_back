package com.xupu.appmanager_back.po;

public class Respon {

    private Boolean isOk;
    private Integer code;
    private String msg;
    private String data;

    public Respon(){

    }
    public Respon(Boolean isOk, Integer code, String msg, String data) {
        this.isOk = isOk;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }

    @Override
    public String toString() {
        return "Respon{" +
                "isOk=" + isOk +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
