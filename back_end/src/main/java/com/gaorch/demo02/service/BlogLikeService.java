package com.gaorch.demo02.service;

import com.gaorch.demo02.entity.BlogLike;
import com.gaorch.demo02.entity.Todo;
import com.gaorch.demo02.entity.User;
import com.gaorch.demo02.mapper.BlogLikeMapper;
import com.gaorch.demo02.mapper.UserMapper;
import com.gaorch.demo02.utils.JwtUtils;
import com.gaorch.demo02.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogLikeService {

    @Autowired
    private BlogLikeMapper blogLikeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    public List<BlogLike> getLikesByBlogId(Integer blogId)
    {
        return blogLikeMapper.selectByBlogId(blogId);
    }

    public Boolean deleteLikesByBlogId(Integer blogId)
    {
        return blogLikeMapper.deleteLikesByBlogId(blogId);
    }

    public Boolean deleteAllByUserId(Integer userId)
    {
        return blogLikeMapper.deleteAllByUserId(userId);
    }


    public Integer getLikeSizeByBlogId(Integer blogId)
    {
        return blogLikeMapper.getLikeSizeByBlogId(blogId);
    }

    public Boolean isMyLike(Integer blogId, Integer userId)
    {
        System.out.println(blogLikeMapper.getMyLikeId(blogId, userId));
        return !blogLikeMapper.getMyLikeId(blogId, userId).isEmpty();
    }

    public Integer getMyLikeId(Integer blogId, Integer userId)
    {
        List<Integer> ids = blogLikeMapper.getMyLikeId(blogId, userId);
        if(ids.isEmpty())
            return -1;
        else
            return ids.getFirst();
    }

    public Result insert(Integer blogId)
    {
        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User user = userMapper.selectByUsername(username);
        Integer userId = user.getId();
        BlogLike blogLike = new BlogLike();
        blogLike.setId(0);
        blogLike.setUserId(userId);
        blogLike.setBlogId(blogId);
        int i = blogLikeMapper.insert(blogLike);
        return i > 0 ? Result.ok() : Result.error();
    }

    public Result delete(Integer id)
    {
        int i = blogLikeMapper.deleteById(id);
        return i > 0 ? Result.ok() : Result.error();
    }
}
