package com.kk.dao;

import com.kk.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StudentMapper {
    @Delete({
        "delete from student",
        "where number = #{number,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer number);

    @Insert({
        "insert into student (number, name, ",
        "sex, birthday, admissionDate, ",
        "collegeId)",
        "values (#{number,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=VARCHAR}, #{birthday,jdbcType=DATE}, #{admissionDate,jdbcType=DATE}, ",
        "#{collegeId,jdbcType=INTEGER})"
    })
    int insert(Student record);

    int insertSelective(Student record);

    @Select({
        "select",
        "number, name, sex, birthday, admissionDate, collegeId",
        "from student",
        "where number = #{number,jdbcType=INTEGER}"
    })
    @ResultMap("com.kk.dao.StudentMapper.BaseResultMap")
    Student selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(Student record);

    @Update({
        "update student",
        "set name = #{name,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=DATE},",
          "admissionDate = #{admissionDate,jdbcType=DATE},",
          "collegeId = #{collegeId,jdbcType=INTEGER}",
        "where number = #{number,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Student record);
}