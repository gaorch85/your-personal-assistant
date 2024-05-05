package com.gaorch.demo02.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {

    private boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

    //私有构造函数
    private Response() { }


    public static Response ok()
    {
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(ResponseCode.SUCCESS);
        response.setMessage("SUCCESS!");
        return response;
    }

    public static Response error()
    {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(ResponseCode.ERROR);
        response.setMessage("ERROR!");
        return response;
    }

    public static Response unAuthorized()
    {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(ResponseCode.UNAUTHORIZED);
        response.setMessage("you are not authorized, please log in!");
        return response;
    }

    public static Response notFound()
    {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(ResponseCode.NOT_FOUND);
        response.setMessage("the resource is not found!");
        return response;
    }

    public Response data(String key, Object value)
    {
        this.data.put(key, value);
        return this;
    }

    public Response setData(Map<String, Object> data)
    {
        this.data = data;
        return this;
    }


}
