package com.gaorch.demo02.controller;

import com.gaorch.demo02.utils.Response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AIController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/ai")
    public Response predict(@RequestBody Input input) {
        String flaskApiUrl = "http://1.92.148.127:5000/predict";
        Map<String, String> flaskResponse = restTemplate.postForObject(flaskApiUrl, input, HashMap.class);
        return Response.ok().data("items",flaskResponse.get("prediction"));
    }
    @Setter
    @Getter
    public static class Input{
        String string;
    }
}
