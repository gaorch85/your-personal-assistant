package com.gaorch.demo02.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaorch.demo02.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE username = #{username}")
    public User selectByUsername(String username);

    @Select("DELETE FROM user WHERE username = #{username}")
    public boolean deleteByUsername(String username);

    // 下面是 mybatis 的方法，mybatis-plus 简化了，继承类即可
    // BaseMapper 自动根据 table 实现功能
//    @Select("SELECT * FROM user")
//    public List<User> getUser();
//
//    @Insert("INSERT INTO user VALUES(#{id}, #{name}, #{password}, #{birthday})")
//    public int insertUser(User user);

}