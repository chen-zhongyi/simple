package com.chen.enums;

import com.chen.interfaces.BaseEnum;

import java.util.Arrays;

public enum Sex implements BaseEnum {

    NO_POINT(100, "无"),
    FEMALE(101, "男"),
    MALE(102, "女");

    private int code;
    private String intro;

    Sex(int code, String intro) {
        this.code = code;
        this.intro = intro;
    }

    public static Sex convert(Integer code) {
        if (code == null) return null;
        return Arrays.stream(values()).filter(c -> c.code() == code).findFirst().orElse(null);
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String intro() {
        return this.intro;
    }
}
