package com.gaorch.demo02.controller;

import com.gaorch.demo02.entity.Course;
import com.gaorch.demo02.entity.ScheduleList;
import com.gaorch.demo02.mapper.ScheduleMapper;
import com.gaorch.demo02.service.CourseService;
import com.gaorch.demo02.service.ScheduleService;
import com.gaorch.demo02.utils.Response;
import com.gaorch.demo02.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/curriculum")
    public Response getAll()
    {
        Result result = scheduleService.getAll();
        return result.isSuccess() ?
                Response.ok().setData(result.getData()) : Response.error();
    }

    @PostMapping("/curriculum")
    public Response insertTodo(@RequestBody ScheduleList scheduleList)
    {
        Result result = scheduleService.updateSchedule(scheduleList);
        return result.isSuccess() ? Response.ok() : Response.error();
    }

}
