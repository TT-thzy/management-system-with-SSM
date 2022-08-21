package com.wt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wt.dao.IUserDao;
import com.wt.pojo.Role;
import com.wt.pojo.UserInfo;
import com.wt.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: hm_ssm
 * @description:
 * @author: Mr.Wang
 * @create: 2021-07-21 19:32
 **/
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] rolesId) {
        for (String s : rolesId) {
            userDao.addRoleToUser(userId,s);
        }
    }

    @Override
    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(s);
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,             //账户是否可用
                true,true,true,getAuthority(userInfo.getRoles()));
        System.out.println(user);
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roleList ) {
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (Role role : roleList) {
            System.out.println(role.getRoleName());
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return simpleGrantedAuthorities;
    }

}
