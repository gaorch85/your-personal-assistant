package com.gaorch.demo02.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.gaorch.demo02.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    // 下面是 mybatis 的方法，mybatis-plus 简化了，继承类即可
    // BaseMapper 自动根据 table 实现功能
//    @Select("SELECT * FROM user")
//    public List<User> getUser();
//
//    @Insert("INSERT INTO user VALUES(#{id}, #{name}, #{password}, #{birthday})")
//    public int insertUser(User user);

}
