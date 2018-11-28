package com.chen.vos;

import org.apache.commons.lang3.StringUtils;

public class StringVO extends OneData {

    private String value;

    public StringVO(String value) {
        this.value = StringUtils.defaultString(value, "default");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
