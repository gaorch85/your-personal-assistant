package com.gaorch.demo02.controller;


import com.gaorch.demo02.entity.Employee;
import com.gaorch.demo02.entity.Todo;
import com.gaorch.demo02.mapper.EmployeeMapper;
import com.gaorch.demo02.mapper.TodoMapper;
import com.gaorch.demo02.utils.JwtUtils;
import com.gaorch.demo02.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TodoController {

    @Autowired
    private TodoMapper todoMapper;
    @Autowired
    private HttpServletRequest request;     //用于解析请求头中的token

    @GetMapping("/todo")
    public Result getAll()
    {
        String token = request.getHeader("X-token");
        //System.out.println(token);
        if(JwtUtils.isAuthrize(token))
        {
            List<Todo> list = todoMapper.selectList(null);

            return Result.ok().data("items", list);
        }
        else
            return Result.unAuthorized();
    }

    @PostMapping("/todo")
    public Result insertTodo(@RequestBody Todo todo)
    {
        String token = request.getHeader("X-token");
        if(JwtUtils.isAuthrize(token))
        {
            int i = todoMapper.insert(todo);
            if(i > 0)
                return Result.ok();
            else
                return Result.error();
        }
        else
            return Result.unAuthorized();
    }

    @PutMapping("/todo")
    public Result updateStatus(@RequestBody Todo todo)
    {
        String token = request.getHeader("X-token");
        if(JwtUtils.isAuthrize(token))
        {
            int i = todoMapper.updateById(todo);
            if(i > 0)
                return Result.ok();
            else
                return Result.error();
        }
        else
            return Result.unAuthorized();
    }

    @DeleteMapping("/todo/{id}")
    public Result deleteByID(@PathVariable Integer id)
    {
        String token = request.getHeader("X-token");
        if(JwtUtils.isAuthrize(token))
        {
            int i = todoMapper.deleteById(id);
            if(i > 0)
                return Result.ok();
            else
                return Result.error();
        }
        else
            return Result.unAuthorized();
    }




}
