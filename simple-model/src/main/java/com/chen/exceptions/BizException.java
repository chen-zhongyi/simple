package com.chen.exceptions;

import com.chen.vos.Result;

public class BizException extends RuntimeException {

    private Result.Code code;

    public BizException(Result.Code code) {
        this.code = code;
    }

    public BizException(Result.Code code, String message) {
        this.code = new Result.Code(code.getCode(), message);
    }

    public Result.Code getCode() {
        return code;
    }

    public void setCode(Result.Code code) {
        this.code = code;
    }
}
