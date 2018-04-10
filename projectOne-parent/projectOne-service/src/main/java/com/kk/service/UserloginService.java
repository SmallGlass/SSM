package com.kk.service;


import com.kk.pojo.User;

/**
 *
 *
 */
public interface UserloginService {

    //根据用户名查找用户
    User findUserByName(String name) throws Exception;

    //添加用户
    void saveUser(User user) throws Exception;

    //根据用户名删除用户
    void removeUserByName(String name) throws Exception;

    //根据用户名更新用户
    void updateUser(String name);

}
