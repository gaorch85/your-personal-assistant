package com.gaorch.demo02.controller;

import com.gaorch.demo02.entity.Employee;
import com.gaorch.demo02.mapper.EmployeeMapper;
import com.gaorch.demo02.mapper.UserMapper;
import com.gaorch.demo02.utils.JwtUtils;
import com.gaorch.demo02.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private HttpServletRequest request;     //用于解析请求头中的token

    @GetMapping("/employee")
    public Result getAllUser()
    {
        String token = request.getHeader("X-token");
        //System.out.println(token);
        if(JwtUtils.isAuthrize(token))
        {
            List<Employee> list = employeeMapper.selectList(null);
            //System.out.println(employees);
            return Result.ok().data("items", list);
        }
        else
            return Result.unAuthorized();
    }

    @PostMapping("/employee")
    public Result insertUser(@RequestBody Employee employee)
    {
        String token = request.getHeader("X-token");
        if(JwtUtils.isAuthrize(token))
        {
            int i = employeeMapper.insert(employee);
            if(i > 0)
                return Result.ok();
            else
                return Result.error();
        }
        else
            return Result.unAuthorized();
    }

    @PutMapping("/employee")
    public Result updateUser(@RequestBody Employee employee)
    {
        String token = request.getHeader("X-token");
        if(JwtUtils.isAuthrize(token))
        {
            int i = employeeMapper.updateById(employee);
            if(i > 0)
                return Result.ok();
            else
                return Result.error();
        }
        else
            return Result.unAuthorized();
    }

    @DeleteMapping("/employee/{id}")
    public Result deleteByID(@PathVariable Integer id)
    {
        String token = request.getHeader("X-token");
        if(JwtUtils.isAuthrize(token))
        {
            int i = employeeMapper.deleteById(id);
            if(i > 0)
                return Result.ok();
            else
                return Result.error();
        }
        else
            return Result.unAuthorized();
    }

}
