package com.gaorch.demo02.controller;

import com.gaorch.demo02.entity.Blog;
import com.gaorch.demo02.service.BlogService;
import com.gaorch.demo02.utils.Response;
import com.gaorch.demo02.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog")
    public Response getAll()
    {
        Result result = blogService.listAll();
        return result.isSuccess() ?
                Response.ok().setData(result.getData()) : Response.error();
    }

    @PostMapping("/blog")
    public Response insertUser(@RequestBody Blog blog)
    {
        Result result = blogService.insert(blog);
        return result.isSuccess() ? Response.ok() : Response.error();
    }

}
