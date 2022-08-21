package com.wt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wt.dao.IOrdersDao;
import com.wt.pojo.Orders;
import com.wt.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: hm_ssm
 * @description:
 * @author: Mr.Wang
 * @create: 2021-07-20 18:13
 **/
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(Integer page, Integer size) throws Exception {
       PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

//    @Override
//    public List<Orders> findAll() throws Exception {
//        return ordersDao.findAll();
//    }


}
