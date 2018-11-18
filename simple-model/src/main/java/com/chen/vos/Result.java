package com.chen.vos;

public class Result<T extends Data> {

    private String status;
    private int code;
    private String message;
    private T data;
    private long systemTime;

    public Result() {}

    private Result(String status, int code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
        systemTime = System.currentTimeMillis();
    }

    public static Result success() {
        return new Result(Status.SUCCESS_TEXT, StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), null);
    }

    public static Result success(Data data) {
        return new Result(Status.SUCCESS_TEXT, StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data);
    }

    public static Result success(Data data, Code code) {
        return new Result(Status.SUCCESS_TEXT, code.getCode(), code.getMessage(), data);
    }

    public static Result failed() {
        return new Result(Status.FAIL, StatusCode.FAIL.getCode(), StatusCode.FAIL.getMessage(), null);
    }

    public static Result failed(Code code) {
        return new Result(Status.FAIL, code.getCode(), code.getMessage(), null);
    }

    public static Result failed(Code code, String message) {
        return new Result(Status.FAIL, code.getCode(), message, null);
    }

    public static final class Status {
        public final static String SUCCESS = "success";
        public final static int SUCCESS_CODE = 20000;
        public final static String SUCCESS_TEXT = "请求成功";
        public final static String FAIL = "fail";
        public final static int FAIL_CODE = 50000;
        public final static String FAIL_TEXT = "服务器出错啦！";
    }

    public static final class StatusCode {
        public static final Code SUCCESS = new Code(Status.SUCCESS_CODE, Status.SUCCESS);
        public static final Code FAIL = new Code(Status.FAIL_CODE, Status.FAIL_TEXT);
        public static final Code BACK_UPDATE_FORBIDDEN = new Code(30001, "无更新权限");
        public static final Code BACK_UPDATE_FAILED = new Code(30002, "更新失败");
        public static final Code BACK_RESTART_FAILED = new Code(30003, "重启失败");
        public static final Code BACK_START_FAILED = new Code(30004, "有项目正在启动");
        public static final Code SYSTEM_APP_UPDATE = new Code(40000, "检测到有版本更新");
        public static final Code SYSTEM_TOKEN_UNVALID = new Code(40001, "accesstoken失效");
        public static final Code SYSTEM_REQUEST_REPEAT = new Code(40002, "重复请求");
        public static final Code SYSTEM_ACCESS_FOBIDDEN = new Code(40003, "无相应权限");
        public static final Code SYSTEM_PARAM_ERROR = new Code(40004, "参数不合法");
        public static final Code PERSON_USERNAME_UNVALID = new Code(40101, "用户名格式错误");
        public static final Code PERSON_PHONE_UNVALID = new Code(40102, "手机号码格式错误");
        public static final Code PERSON_EMAIL_UNVALID = new Code(40103, "邮箱格式错误");
        public static final Code PERSON_PASSWORD_UNVALID = new Code(40104, "密码格式错误");
        public static final Code PERSON_USERNAME_EXIST = new Code(40105, "该用户名已被使用");
        public static final Code PERSON_PHONE_EXIST = new Code(40106, "该手机号码已被使用");
        public static final Code PERSON_EMAIL_EXIST = new Code(40107, "该邮箱已被使用");
        public static final Code PERSON_PASSWORD_ERROR = new Code(40108, "密码错误");
        public static final Code PERSON_CAPTCHA_ERROR = new Code(40109, "验证码错误");
        public static final Code PERSON_ACCOUNT_NOTEXIST = new Code(40110, "用户不存在");
        public static final Code PERSON_ACCOUNT_BINDED = new Code(40111, "账号已被绑定");
    }

    public static class Code {
        private int code;
        private String message;

        public Code(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(long systemTime) {
        this.systemTime = systemTime;
    }
}
