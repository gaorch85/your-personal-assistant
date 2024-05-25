package com.gaorch.demo02.service;

import com.gaorch.demo02.entity.BlogView;
import com.gaorch.demo02.mapper.BlogViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogViewService {

    @Autowired
    BlogViewMapper blogViewMapper;

    public Boolean deleteByUserId(Integer userId)
    {
        return blogViewMapper.deleteByUserId(userId);
    }

    public Boolean deleteByBlogId(Integer blogId)
    {
        return blogViewMapper.deleteByBlogId(blogId);
    }

    public Integer countViewsByBlogId(Integer blogId)
    {
        return blogViewMapper.getViewSizeByBlogId(blogId);
    }

    public Boolean insert(Integer userId, Integer blogId)
    {
        if(blogViewMapper.getBlogViewByBlogIdAndUserId(blogId, userId)==null)
        {
            BlogView blogView = new BlogView();
            blogView.setId(0);
            blogView.setBlogId(blogId);
            blogView.setUserId(userId);
            blogViewMapper.insert(blogView);
            return true;
        }
        else
            return false;
    }
}
