package com.example.wanhao.apiapp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wanhao on 2018/1/20.
 */

public class MessageResponse {
    @SerializedName("code")
    String code;
    @SerializedName("msg")
    String msg;
    @SerializedName("newslist")
    List<Message> data;

    public void JsonTo(String json){

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Message> getAr() {
        return data;
    }

    public void setAr(List<Message> data) {
        this.data = data;
    }
}
