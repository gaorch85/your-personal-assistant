package com.gaorch.demo02.service;

import com.gaorch.demo02.entity.Blog;
import com.gaorch.demo02.entity.BlogFavorite;
import com.gaorch.demo02.entity.BlogLike;
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
    private HttpServletRequest request;


    public List<Blog> listAll()
    {
        return blogMapper.selectList(null);
    }

    public  List<Blog> addOtherData(List<Blog> blog)
    {
        for(Blog curBlog: blog)
        {
            Integer blogId = curBlog.getId();
            curBlog.setLikes(blogLikeService.getLikesByBlogId(blogId));
            curBlog.setFavorites(blogFavoriteService.getFavoritessByBlogId(blogId));
            curBlog.setComments(blogCommentService.getCommentsByBlogId(blogId));
        }
        return blog;
    }

    public Result listRecommendAll()
    {
        System.out.println("按推荐排序");
        List<Blog> list = addOtherData(listAll());
        list.sort(new Comparator<Blog>() {
            @Override
            public int compare(Blog blog1, Blog blog2) {
                return Integer.compare(blog2.getRecommendIndex(), blog1.getRecommendIndex());
            }
        });
        return Result.ok().data("items", list);
    }

    public Result listLatestAll()
    {
        System.out.println("按时间排序");
        List<Blog> list = addOtherData(listAll());
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
        System.out.println("按我的显示");
        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        List<Blog> list = listAll();
        System.out.println(list);
        Iterator<Blog> iterator = list.iterator();
        while (iterator.hasNext()) {
            Blog curBlog = iterator.next();
            if (!Objects.equals(curBlog.getUsername(), username)) {
                System.out.println("不符合条件，移除");
                iterator.remove();
            }
        }
        System.out.println(list);
        return Result.ok().data("items", list);
    }

    public Result listMyLikeAll()
    {
        System.out.println("按我的的点赞显示");
        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User user = userMapper.selectByUsername(username);
        Integer userId = user.getId();
        List<Blog> list = addOtherData(listAll());
        System.out.println(list);
        Iterator<Blog> iterator = list.iterator();
        while (iterator.hasNext()) {
            Blog curBlog = iterator.next();
            boolean isLike = false;
            List<BlogLike> curBlogLikes = curBlog.getLikes();
            for(BlogLike like: curBlogLikes)
            {
                if(like.getUserId().equals(userId))
                    isLike = true;
            }
            if(!isLike)
                iterator.remove();
        }
        System.out.println(list);
        return Result.ok().data("items", list);
    }

    public Result listMyFavoriteAll()
    {
        System.out.println("按我的的点赞显示");
        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User user = userMapper.selectByUsername(username);
        Integer userId = user.getId();
        List<Blog> list = addOtherData(listAll());
        System.out.println(list);
        Iterator<Blog> iterator = list.iterator();
        while (iterator.hasNext()) {
            Blog curBlog = iterator.next();
            boolean isFavorite = false;
            List<BlogFavorite> curBlogFavorites = curBlog.getFavorites();
            for(BlogFavorite favorite: curBlogFavorites)
            {
                if(favorite.getUserId().equals(userId))
                    isFavorite = true;
            }
            if(!isFavorite)
                iterator.remove();
        }
        System.out.println(list);
        return Result.ok().data("items", list);
    }

    public Result getPostById(Integer postId)
    {
        Blog blog = blogMapper.selectById(postId);
        blog.setLikes(blogLikeService.getLikesByBlogId(postId));
        blog.setFavorites(blogFavoriteService.getFavoritessByBlogId(postId));
        blog.setComments(blogCommentService.getCommentsByBlogId(postId));

        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User user = userMapper.selectByUsername(username);
        Integer userId = user.getId();

        for(BlogLike curLike: blog.getLikes())
            if(curLike.getUserId().equals(userId)) {
                blog.setMyLike(true);
                blog.setMyLikeId(curLike.getId());
                break;
            }
        for(BlogFavorite curFavorite: blog.getFavorites())
            if(curFavorite.getUserId().equals(userId)) {
                blog.setMyFavorite(true);
                blog.setMyFavoriteId(curFavorite.getId());
                break;
            }
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

}
