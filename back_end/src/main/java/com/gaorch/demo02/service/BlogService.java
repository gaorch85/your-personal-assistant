package com.gaorch.demo02.service;

import com.gaorch.demo02.entity.Blog;
import com.gaorch.demo02.entity.User;
import com.gaorch.demo02.mapper.*;
import com.gaorch.demo02.utils.JwtUtils;
import com.gaorch.demo02.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;
import java.util.Objects;

@Service
public class BlogService
{
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogLikeService blogLikeService;
    @Autowired
    private BlogFavoriteService blogFavoriteService;
    @Autowired
    private BlogCommentService blogCommentService;
    @Autowired
    private BlogViewService blogViewService;

    @Autowired
    private HttpServletRequest request;


    public List<Blog> listAll()
    {
        return blogMapper.selectBasicBlogs();
    }

    public List<Blog> addOtherDataSize(List<Blog> blogs)
    {

        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User user = userMapper.selectByUsername(username);
        Integer userId = user.getId();


        for(Blog curBlog: blogs)
        {
            Integer blogId = curBlog.getId();
            curBlog.setLikeSize(blogLikeService.getLikeSizeByBlogId(blogId));
            curBlog.setFavoriteSize(blogFavoriteService.getFavoriteSizeByBlogId(blogId));
            curBlog.setCommentSize(blogCommentService.getCommentSizeByBlogId(blogId));
            curBlog.setViewSize(blogViewService.countViewsByBlogId(blogId));
            curBlog.setMyLike(blogLikeService.isMyLike(blogId, userId));
            curBlog.setMyFavorite(blogFavoriteService.isMyFavorite(blogId, userId));
            curBlog.setMyBlog(Objects.equals(username, curBlog.getUsername()));
        }
        return blogs;
    }

    public List<Blog> filterDisplayable(List<Blog> blogs)
    {
        blogs.removeIf(curBlog -> !curBlog.getIsPublic() && !curBlog.getMyBlog());
        return blogs;
    }

    public List<Blog> getRecommendBlogs()
    {

        List<Blog> list = filterDisplayable(addOtherDataSize(listAll()));

        list.sort(new Comparator<Blog>() {
            @Override
            public int compare(Blog blog1, Blog blog2) {
                return Integer.compare(blog2.getRecommendIndex(), blog1.getRecommendIndex());
            }
        });
        return list;
    }

    public Result listRecommendAll()
    {
        System.out.println("按推荐排序");
        List<Blog> list = this.getRecommendBlogs();
        return Result.ok().data("items", list);
    }

    public Result listLatestAll()
    {
        System.out.println("按时间排序");
        List<Blog> list = filterDisplayable(addOtherDataSize(listAll()));
        list.sort(new Comparator<Blog>() {
            @Override
            public int compare(Blog blog1, Blog blog2) {
                LocalDateTime dateTime1 = LocalDateTime.parse(blog1.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime dateTime2 = LocalDateTime.parse(blog2.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return dateTime2.compareTo(dateTime1);
            }
        });
        return Result.ok().data("items", list);
    }


    public Result listMyBlogAll()
    {

        List<Blog> list = this.getRecommendBlogs();
        list.removeIf(curBlog -> !curBlog.getMyBlog());
        return Result.ok().data("items", list);
    }

    public Result listMyLikeAll()
    {
        List<Blog> list = this.getRecommendBlogs();
        list.removeIf(curBlog -> !curBlog.getMyLike());
        return Result.ok().data("items", list);
    }

    public Result listMyFavoriteAll()
    {
        List<Blog> list = this.getRecommendBlogs();
        list.removeIf(curBlog -> !curBlog.getMyFavorite());
        return Result.ok().data("items", list);
    }

    public Result getPostById(Integer blogId)
    {
        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User user = userMapper.selectByUsername(username);
        Integer userId = user.getId();

        Blog blog = blogMapper.selectById(blogId);
        if (blog == null || (!blog.getIsPublic() && !blog.getUsername().equals(username)))
            return Result.error();

        blog.setLikeSize(blogLikeService.getLikeSizeByBlogId(blogId));
        blog.setFavoriteSize(blogFavoriteService.getFavoriteSizeByBlogId(blogId));
        blog.setViewSize(blogViewService.countViewsByBlogId(blogId));

        if(blogLikeService.isMyLike(blog.getId(), userId))
        {
            blog.setMyLike(true);
            blog.setMyLikeId(blogLikeService.getMyLikeId(blog.getId(), userId));
        }

        if(blogFavoriteService.isMyFavorite(blog.getId(), userId))
        {
            blog.setMyFavorite(true);
            blog.setMyFavoriteId(blogFavoriteService.getMyFavoriteId(blog.getId(), userId));
        }

        blogViewService.insert(userId, blogId);

        if(blog.getUsername().equals(username))
            blog.setMyBlog(true);

        return Result.ok().data("items", blog);
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

    public Result delete(Integer blogId)
    {
        blogLikeService.deleteLikesByBlogId(blogId);
        blogCommentService.deleteCommentsByBlogId(blogId);
        blogFavoriteService.deleteFavoritesByBlogId(blogId);
        blogViewService.deleteByBlogId(blogId);
        return blogMapper.deleteById(blogId) > 0 ? Result.ok() : Result.error();
    }

    public Result changeBlogById(Integer blogId)
    {
        Blog blog = blogMapper.selectById(blogId);
        blog.setIsPublic(!blog.getIsPublic());
        int i = blogMapper.updateById(blog);
        return i > 0 ? Result.ok() : Result.error();
    }


    public Boolean deleteAllByUsername(String username)
    {
        List<Blog> blogs = blogMapper.selectByUsername(username);
        Iterator<Blog> iterator = blogs.iterator();
        while (iterator.hasNext()) {
            Blog curBlog = iterator.next();
            this.delete(curBlog.getId());
        }

        User user = userMapper.selectByUsername(username);
        Integer userId = user.getId();
        blogLikeService.deleteAllByUserId(userId);
        blogFavoriteService.deleteAllByUserId(userId);
        blogCommentService.deleteAllByUserId(userId);
        blogViewService.deleteByUserId(userId);

        return true;
    }

}
