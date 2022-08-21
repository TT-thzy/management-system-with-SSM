package com.wt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wt.dao.IPermissionDao;
import com.wt.pojo.Permission;
import com.wt.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: hm_ssm
 * @description:
 * @author: Mr.Wang
 * @create: 2021-07-30 13:05
 **/
@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String permissionId) {
        return permissionDao.findByPermissionId(permissionId);
    }

    @Override
    public void deletePermission(String permissionId) {
        permissionDao.deletePermission(permissionId);
    }


}
