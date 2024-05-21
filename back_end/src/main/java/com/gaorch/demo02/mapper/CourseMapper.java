package com.gaorch.demo02.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaorch.demo02.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {

    @Select("SELECT * FROM course WHERE user_id = #{userId}")
    List<Course> selectListByUserId(Integer userId);

    @Select("SELECT * FROM course WHERE name = #{courseName}")
    Course selectByCourseName(String courseName);

    @Delete("DELETE FROM course WHERE user_id = #{userId}")
    Boolean deleteByUserId(Integer userId);

}
