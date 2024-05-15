package com.gaorch.demo02.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class Blog {
    private Integer id;
    private String username;
    private String time;
    private String title;
    private String content;
    @TableField(exist = false)
    private List<BlogLike> likes;
    @TableField(exist = false)
    private List<BlogFavorite> favorites;
    @TableField(exist = false)
    private List<BlogComment> comments;
    @TableField(exist = false)
    private Boolean myLike = false;
    @TableField(exist = false)
    private Integer myLikeId;
    @TableField(exist = false)
    private Boolean myFavorite = false;
    @TableField(exist = false)
    private Integer myFavoriteId;

    @JsonIgnore
    public int getRecommendIndex()
    {
        Integer likesSize = this.getLikes()!=null?this.getLikes().size():0;
        Integer favoritesSize = this.getFavorites()!=null?this.getFavorites().size():0;
        Integer commentsSize = this.getComments()!=null?this.getComments().size():0;
        return likesSize + favoritesSize + commentsSize;
    }

}
