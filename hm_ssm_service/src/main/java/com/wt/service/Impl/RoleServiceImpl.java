package com.wt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wt.dao.IRoleDao;
import com.wt.pojo.Permission;
import com.wt.pojo.Role;
import com.wt.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: hm_ssm
 * @description:
 * @author: Mr.Wang
 * @create: 2021-07-30 11:47
 **/
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(int page,int size) throws Exception{
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception{
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) throws Exception{
        return roleDao.findById(roleId);
    }

    @Override
    public void deleteRole(String roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionsId) {
        for (String s : permissionsId) {
            roleDao.addPermissionToRole(roleId,s);
        }
    }
}
