package com.kk.service.impl;

import com.kk.dao.StudentMapper;
import com.kk.pojo.StudentInfo;
import com.kk.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yzb on 2018/4/6.
 */
@Service
public class StudentInfoImpl implements StudentInfoService {
    @Autowired
    private StudentMapper studentMapper;
    public List<StudentInfo> findStudentsAll(int offset, int pageSize) {
        return studentMapper.findStudentsAll(offset,pageSize);
    }

    public int countStudentsAll() {
        return studentMapper.countStudentsAll();
    }
    public void deleteStudentById(int id){
        studentMapper.deleteStudentById(id);
    }
}
