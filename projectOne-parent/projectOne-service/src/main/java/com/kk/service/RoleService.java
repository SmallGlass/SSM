package com.kk.service;


import com.kk.pojo.Role;

/**
 *  Role 权限表Service层
 */
public interface RoleService {

    Role findRoleById(Integer id) throws Exception;

}
