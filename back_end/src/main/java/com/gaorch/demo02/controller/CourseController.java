package com.gaorch.demo02.controller;

import com.gaorch.demo02.entity.Course;
import com.gaorch.demo02.service.CourseService;
import com.gaorch.demo02.utils.Response;
import com.gaorch.demo02.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/curriculum/course")
    public Response getAll()
    {
        Result result = courseService.getAll();
        return result.isSuccess() ?
                Response.ok().setData(result.getData()) : Response.error();
    }

    @PostMapping("/curriculum/course")
    public Response insertTodo(@RequestBody Course course)
    {
        Result result = courseService.insert(course);
        return result.isSuccess() ? Response.ok() : Response.error();
    }


    @DeleteMapping("/curriculum/course/{id}")
    public Response deleteByID(@PathVariable Integer id)
    {
        Result result = courseService.deleteById(id);
        return result.isSuccess() ? Response.ok() : Response.error();
    }
}
