package com.wt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wt.dao.ISysLogDao;
import com.wt.pojo.SysLog;
import com.wt.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: hm_ssm
 * @description:
 * @author: Mr.Wang
 * @create: 2021-08-01 13:48
 **/

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
