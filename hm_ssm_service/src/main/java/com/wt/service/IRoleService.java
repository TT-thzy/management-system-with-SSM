package com.wt.service;

import com.wt.pojo.Permission;
import com.wt.pojo.Role;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * @program: mybatisTest
 * @description:
 * @author: Mr.Wang
 * @create: 2021-03-27 17:26
 **/
public interface IRoleService {

    List<Role> findAll(int page,int size) throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    void deleteRole(String roleId);

    List<Permission> findOtherPermission(String roleId);

    void addPermissionToRole(String roleId, String[] permissionsId);
}
