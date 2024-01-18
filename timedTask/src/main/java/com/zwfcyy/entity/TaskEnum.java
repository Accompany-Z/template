package com.zwfcyy.entity;


/**
 * @description: 任务配置
 * @author: zwf
 * @create: 2023-09-09 14:31
 **/
public enum TaskEnum {

    NORMAL(1, "正常"),
    PAUSED(0, "暂停");
    private int code;
    private String name;

    TaskEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
