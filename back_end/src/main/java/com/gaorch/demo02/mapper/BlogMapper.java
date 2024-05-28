package com.gaorch.demo02.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaorch.demo02.entity.Blog;
import com.gaorch.demo02.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    @Select("SELECT id, user_id, time, title, is_public FROM blog")
    public List<Blog> selectBasicBlogs();

    @Select("SELECT * FROM blog WHERE user_id = #{userId}")
    public List<Blog> selectByUserId(Integer userId);


}