package com.kk.service;


import com.kk.pojo.StudentInfo;

import java.util.List;

/**
 * Created by yzb on 2018/4/6.
 */
public interface StudentInfoService {
    List<StudentInfo> findStudentsAll(int offset, int pageSize);
    int countStudentsAll();
    void deleteStudentById(int id);
}
