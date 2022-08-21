package com.wt.pojo;

import com.wt.utils.DateUtils;

import java.util.Date;

/**
 * @program: hm_ssm
 * @description: 日志
 * @author: Mr.Wang
 * @create: 2021-08-01 13:12
 **/
public class SysLog {
    private String id;
    private Date visitTime; //访问时间
    private String visitTimeStr;
    private String username;    //当前操作者
    private String ip;          //ip地址
    private String url;         //访问路径
    private Long executionTime; //执行时间
    private String method;      //执行方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {
        if (visitTime!=null){
            visitTimeStr= DateUtils.dateToString(visitTime,"yyyy-MM-dd hh:mm:ss");
        }
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
