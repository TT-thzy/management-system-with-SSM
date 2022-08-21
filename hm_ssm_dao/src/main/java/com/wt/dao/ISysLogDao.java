package com.wt.dao;

import com.wt.pojo.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: mybatisTest
 * @description:
 * @author: Mr.Wang
 * @create: 2021-03-27 17:26
 **/

public interface ISysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    @Select("select * from syslog")
//    @Results({
//            @Result(id=true,column="id",property="id"),
//            @Result(column="visitTime",property="visitTime"),
//            @Result(column="ip",property="ip"),
//            @Result(column="url",property="url"),
//            @Result(column="executionTime",property="executionTime"),
//            @Result(column="method",property="method"),
//            @Result(column="username",property="username")
//    })
    List<SysLog> findAll() throws Exception;
}
