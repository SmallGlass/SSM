package com.kk.dao;

import com.kk.pojo.StudentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yzb on 2018/4/6.
 */
public interface StudentMapper {
    List<StudentInfo> findStudentsAll(@Param("offset")int offset, @Param("pageSize") int pageSize);
    int countStudentsAll();
    void deleteStudentById(int id);
}
