package com.wt.service;

import com.wt.pojo.SysLog;

import java.util.List;

/**
 * @program: mybatisTest
 * @description:
 * @author: Mr.Wang
 * @create: 2021-03-27 17:26
 **/
public interface SysLogService {
    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page,Integer size) throws Exception;

}
