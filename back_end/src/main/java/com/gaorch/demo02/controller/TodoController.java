package com.gaorch.demo02.controller;

import com.gaorch.demo02.entity.Todo;
import com.gaorch.demo02.service.TodoService;
import com.gaorch.demo02.utils.Response;
import com.gaorch.demo02.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todo")
    public Response getAll()
    {
        Result result = todoService.listAll();
        return result.isSuccess() ?
                Response.ok().setData(result.getData()) : Response.error();
    }

    @PostMapping("/todo")
    public Response insertTodo(@RequestBody Todo todo)
    {
        Result result = todoService.insert(todo);
        return result.isSuccess() ? Response.ok() : Response.error();
    }

    @PutMapping("/todo")
    public Response updateStatus(@RequestBody Todo todo)
    {
        Result result = todoService.updateStatus(todo);
        return result.isSuccess() ? Response.ok() : Response.error();
    }

    @DeleteMapping("/todo/{id}")
    public Response deleteByID(@PathVariable Integer id)
    {
        Result result = todoService.deleteById(id);
        return result.isSuccess() ? Response.ok() : Response.error();
    }
}
