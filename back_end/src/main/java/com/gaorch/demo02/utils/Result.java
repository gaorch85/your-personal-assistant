package com.gaorch.demo02.utils;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

    public boolean isSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }


    //私有构造函数
    private Result() { }


    public static Result ok()
    {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("SUCCESS!");
        return result;
    }

    public static Result error()
    {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("ERROR");
        return result;
    }

    public static Result unAuthorized()
    {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.UNAUTHORIZED);
        result.setMessage("you are not authorized, please log in!");
        return result;
    }

    public static Result notFound()
    {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.NOT_FOUND);
        result.setMessage("the resource is not found!");
        return result;
    }

    public Result data(String key, Object value)
    {
        this.data.put(key, value);
        return this;
    }


}
