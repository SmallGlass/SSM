package com.kk.service.impl;


import com.kk.dao.RoleMapper;
import com.kk.pojo.Role;
import com.kk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role findRoleById(Integer id) throws Exception {
        return roleMapper.findRoleById(id);
    }
}
