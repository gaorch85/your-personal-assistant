package com.gaorch.demo02.service;

import com.gaorch.demo02.entity.Blog;
import com.gaorch.demo02.mapper.BlogMapper;
import com.gaorch.demo02.utils.JwtUtils;
import com.gaorch.demo02.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogService
{
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private HttpServletRequest request;

    public Result listAll()
    {
        List<Blog> list = blogMapper.selectList(null);
        return Result.ok().data("items", list);
    }

    public Result insert(Blog blog)
    {
        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        blog.setUsername(username);
        blog.setId(0);
        int i = blogMapper.insert(blog);
        return i > 0 ? Result.ok() : Result.error();
    }

}
