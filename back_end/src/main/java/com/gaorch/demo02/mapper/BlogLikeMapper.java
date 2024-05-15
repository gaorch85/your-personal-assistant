package com.gaorch.demo02.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaorch.demo02.entity.BlogLike;
import com.gaorch.demo02.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogLikeMapper extends BaseMapper<BlogLike> {
    @Select("SELECT * FROM blog_like WHERE blog_id = #{blogId}")
    public List<BlogLike> selectByBlogId(Integer blogId);

}
