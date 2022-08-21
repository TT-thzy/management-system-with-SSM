package com.wt.service;

import com.wt.pojo.Permission;

import java.util.List;

/**
 * @program: mybatisTest
 * @description:
 * @author: Mr.Wang
 * @create: 2021-03-27 17:26
 **/
public interface IPermissionService {

    List<Permission> findAll(int page, int size) throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(String permissionId);

    void deletePermission(String permissionId);
}
