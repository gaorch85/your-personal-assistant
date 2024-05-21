package com.gaorch.demo02.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaorch.demo02.entity.Course;
import com.gaorch.demo02.entity.Schedule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScheduleMapper extends BaseMapper<Schedule> {
    @Select("SELECT * FROM schedule WHERE user_id = #{userId}")
    List<Schedule> selectListByUserId(Integer userId);

    @Delete("DELETE FROM schedule WHERE user_id = #{userId}")
    Boolean deleteByUserId(Integer userId);

}