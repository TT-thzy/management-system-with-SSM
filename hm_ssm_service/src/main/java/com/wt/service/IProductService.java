package com.wt.service;

import com.wt.pojo.Product;

import java.util.List;

/**
 * @program: mybatisTest
 * @description:
 * @author: Mr.Wang
 * @create: 2021-03-27 17:26
 **/
public interface IProductService {

//    List<Product> findAll() throws Exception;

    List<Product> findAll(Integer page,Integer size) throws Exception;

    void save(Product product) throws Exception;
}
