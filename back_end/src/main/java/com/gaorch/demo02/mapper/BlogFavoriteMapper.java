package com.gaorch.demo02.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaorch.demo02.entity.BlogFavorite;
import com.gaorch.demo02.entity.BlogLike;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogFavoriteMapper extends BaseMapper<BlogFavorite> {
    @Select("SELECT * FROM blog_favorite WHERE blog_id = #{blogId}")
    public List<BlogFavorite> selectByBlogId(Integer blogId);

}
