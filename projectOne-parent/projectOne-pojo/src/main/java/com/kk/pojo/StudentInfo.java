package com.kk.pojo;

/**
 * Created by yzb on 2018/4/6.
 * 封装成前台需要的字段
 */
public class StudentInfo extends Student {
    //学院名字
    private String collegeName;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
