package com.gaorch.demo02.controller;

import com.gaorch.demo02.entity.Blog;
import com.gaorch.demo02.entity.Employee;
import com.gaorch.demo02.mapper.BlogMapper;
import com.gaorch.demo02.mapper.EmployeeMapper;
import com.gaorch.demo02.utils.JwtUtils;
import com.gaorch.demo02.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private HttpServletRequest request;     //用于解析请求头中的token

    @GetMapping("/blog")
    public Result getAll()
    {
        String token = request.getHeader("X-token");
        if(JwtUtils.isAuthrize(token))
        {
            List<Blog> list = blogMapper.selectList(null);
            return Result.ok().data("items",list);

        }
        else
            return Result.unAuthorized();
    }

    @PostMapping("/blog")
    public Result insertUser(@RequestBody Blog blog)
    {
        String token = request.getHeader("X-token");
        if(JwtUtils.isAuthrize(token))
        {

            blog.setUsername(JwtUtils.getClaimsByToken(token).getSubject());
            blog.setId(0);
            System.out.println(blog);
            int i = blogMapper.insert(blog);
            if(i > 0)
                return Result.ok();
            else
                return Result.error();
        }
        else
            return Result.unAuthorized();
    }

}
