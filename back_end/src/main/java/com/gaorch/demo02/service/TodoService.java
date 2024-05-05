package com.gaorch.demo02.service;

import com.gaorch.demo02.entity.Todo;
import com.gaorch.demo02.mapper.TodoMapper;
import com.gaorch.demo02.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService
{
    @Autowired
    private TodoMapper todoMapper;

    public Result listAll()
    {
        return Result.ok().data("items",todoMapper.selectList(null));
    }

    public Result insert(Todo todo)
    {
        int i = todoMapper.insert(todo);
        return i > 0 ? Result.ok() : Result.error();
    }

    public Result updateStatus(Todo todo)
    {
        int i = todoMapper.updateById(todo);
        return i > 0 ? Result.ok() : Result.error();
    }

    public Result deleteById(Integer id)
    {
        int i = todoMapper.deleteById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

}
