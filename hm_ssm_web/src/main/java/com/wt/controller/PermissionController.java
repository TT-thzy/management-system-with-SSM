package com.wt.controller;

import com.github.pagehelper.PageInfo;
import com.wt.pojo.Permission;
import com.wt.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @program: hm_ssm
 * @description:
 * @author: Mr.Wang
 * @create: 2021-07-30 13:00
 **/
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")  Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> list= permissionService.findAll(page,size);
        PageInfo<Permission> pageInfo = new PageInfo<>(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String permissionId){
        ModelAndView mv = new ModelAndView();
        Permission permission=permissionService.findById(permissionId);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("deletePermission.do")
    public String deletePermission(@RequestParam(name = "id",required = true) String permissionId){
        permissionService.deletePermission(permissionId);
        return "redirect:findAll.do";
    }
}
