package com.gaorch.demo02.controller;

import com.gaorch.demo02.service.AIService;
import com.gaorch.demo02.utils.Response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private AIService aiService;

    @PostMapping("/ai/request")
    public Response getReplay(@RequestBody Input input)
    {
        Integer times = aiService.getAITimes();
        if(times > 0)
        {
            String flaskApiUrl = "http://1.92.148.127:5000/predict";
            Map<String, String> flaskResponse = restTemplate.postForObject(flaskApiUrl, input, HashMap.class);
            aiService.decreaseAITimes();
            return Response.ok().data("items",flaskResponse.get("prediction"));
        }
        else
            return Response.unAuthorized();
    }

    @GetMapping("ai/times")
    public Response getTimes()
    {
        return Response.ok().data("items", aiService.getAITimes());
    }
    @Setter
    @Getter
    public static class Input{
        String string;
    }
}
