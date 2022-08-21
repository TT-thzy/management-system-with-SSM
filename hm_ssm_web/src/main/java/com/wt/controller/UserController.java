package com.wt.controller;

import com.github.pagehelper.PageInfo;
import com.wt.pojo.Role;
import com.wt.pojo.UserInfo;
import com.wt.service.IUserService;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.nio.cs.US_ASCII;

import java.util.List;

/**
 * @program: hm_ssm
 * @description:
 * @author: Mr.Wang
 * @create: 2021-07-29 19:33
 **/

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }


    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='tom'")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> userList = userService.findAll(page, size);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    //查询用户和该用户能添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(userId);
        List<Role> roleList= userService.findOtherRoles(userId);
        mv.addObject("user",user);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true)String[] rolesId){
        userService.addRoleToUser(userId,rolesId);
        return "redirect:findAll.do";
    }

    @RequestMapping("/deleteUser.do")
    public String deleteUser(@RequestParam(name = "id",required = true) String userId){
        userService.deleteUser(userId);
        return "redirect:findAll.do";
    }
}
