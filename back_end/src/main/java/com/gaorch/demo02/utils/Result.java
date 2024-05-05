package com.gaorch.demo02.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result
{
    private boolean success;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    private Result() { }

    public Result data(String key, Object value)
    {
        this.data.put(key, value);
        return this;
    }

    public static Result ok()
    {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("SUCCESS!");
        return result;
    }

    public static Result error()
    {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("ERROR!");
        return result;
    }



}
