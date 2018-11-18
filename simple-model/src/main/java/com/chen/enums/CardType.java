package com.chen.enums;

import com.chen.interfaces.BaseEnum;

import java.util.Arrays;

public enum CardType implements BaseEnum {

    ID_CARD(100, "省份证"),
    BANK(101, "银行卡");

    private int code;
    private String intro;

    CardType(int code, String intro) {
        this.code = code;
        this.intro = intro;
    }

    public static CardType convert(Integer code) {
        if (code == null) {
            return null;
        }
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

