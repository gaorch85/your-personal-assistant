package com.gaorch.demo02.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

@Data
public class BlogComment {
    private Integer id;
    private Integer userId;
    private Integer blogId;
    private String content;
    private String createdAt;
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private Boolean myComment;

    public void setMyComment(Integer userId)
    {
        this.myComment = userId.equals(this.getUserId());
    }
}
