package com.wt.controller;

import com.github.pagehelper.PageInfo;
import com.wt.pojo.Product;
import com.wt.service.IProductService;
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
 * @create: 2021-07-10 17:49
 **/
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    //添加产品
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

    //查询全部产品（分页前）
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView model=new ModelAndView();
//        List<Product> products = productService.findAll();
//        model.addObject("productList",products);
//        model.setViewName("product-list1");
//        return model;
//    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(page, size);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-page-list1");
        return mv;
    }
}


