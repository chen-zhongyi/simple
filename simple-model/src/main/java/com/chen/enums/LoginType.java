package com.chen.enums;

import com.chen.interfaces.BaseEnum;

import java.util.Arrays;

public enum LoginType implements BaseEnum {

    USERNAME(100, "用户名密码登录"),
    PHONE(101, "手机验证码登录");

    private int code;
    private String intro;

    LoginType(int code, String intro) {
        this.code = code;
        this.intro = intro;
    }

    public static LoginType convert(Integer code) {
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
