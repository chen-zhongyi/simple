package com.chen.enums;

import com.chen.interfaces.BaseEnum;

import java.util.Arrays;

public enum PersonType implements BaseEnum {

    BACK(100, "后台用户");

    private int code;
    private String intro;

    PersonType(int code, String intro) {
        this.code = code;
        this.intro = intro;
    }

    public static PersonType convert(Integer code) {
        if (code == null)   return null;
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
