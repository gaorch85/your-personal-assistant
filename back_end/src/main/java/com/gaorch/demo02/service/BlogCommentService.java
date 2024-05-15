package com.gaorch.demo02.service;

import com.gaorch.demo02.entity.User;
import com.gaorch.demo02.mapper.BlogCommentMapper;
import com.gaorch.demo02.entity.BlogComment;
import com.gaorch.demo02.mapper.UserMapper;
import com.gaorch.demo02.utils.JwtUtils;
import com.gaorch.demo02.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCommentService {

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    public List<BlogComment> getCommentsByBlogId(Integer blogId)
    {
        List<BlogComment> comments = blogCommentMapper.selectByBlogId(blogId);
        for(BlogComment curComment: comments)
        {
            User user = userMapper.selectById(curComment.getUserId());
            curComment.setUsername(user.getUsername());
        }
        return comments;
    }

    public Result insert(Integer postId, String comment)
    {
        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User user = userMapper.selectByUsername(username);
        Integer userId = user.getId();

        BlogComment blogComment = new BlogComment();
        blogComment.setUserId(userId);
        blogComment.setBlogId(postId);
        blogComment.setId(0);
        blogComment.setContent(comment);
        int i = blogCommentMapper.insert(blogComment);
        return i > 0 ? Result.ok() : Result.error();
    }

    public Result delete(Integer id)
    {
        int i = blogCommentMapper.deleteById(id);
        return i > 0 ? Result.ok() : Result.error();
    }
}
