package com.kk.service.impl;


import com.kk.dao.UserloginMapper;
import com.kk.pojo.User;
import com.kk.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class UserloginServiceImpl implements UserloginService {

    @Autowired
    private UserloginMapper userloginMapper;


    public User findUserByName(String name) throws Exception {
        return userloginMapper.findUserByName(name);
    }

    public void saveUser(User user) throws Exception {
        userloginMapper.saveUser(user);
    }

    public void removeUserByName(String name) throws Exception {
        userloginMapper.removeUserByName(name);
    }

    public void updateUser(String name) {
        userloginMapper.updateUser(name);
    }
}
