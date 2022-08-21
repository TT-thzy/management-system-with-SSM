package com.wt.service;

import com.wt.pojo.Orders;

import java.util.List;

/**
 * @program: mybatisTest
 * @description:
 * @author: Mr.Wang
 * @create: 2021-03-27 17:26
 **/
public interface IOrdersService {

//    List<Orders> findAll() throws Exception;
      List<Orders> findAll(Integer page,Integer size) throws Exception;
}
