package com.wt.controller;

import com.wt.pojo.SysLog;
import com.wt.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @program: hm_ssm
 * @description: 日志切面类
 * @author: Mr.Wang
 * @create: 2021-08-01 13:14
 **/
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date startTime;     //访问时间
    private Class executionClass;   //执行的类
    private Method executionMethod; //执行的方法

    @Before("execution(* com.wt.controller.*.*(..))")
    public void doBefore(JoinPoint point) throws Exception {
        startTime = new Date();       //获取访问时间

        executionClass = point.getTarget().getClass();    //获取访问的类

        String methodName = point.getSignature().getName(); //获取访问的方法名字

        Object[] args = point.getArgs();    //获取访问的方法的形参

        //获取执行的方法
        if (args == null && args.length == 0) {
            executionMethod = executionClass.getMethod(methodName);
        } else {
            Class[] s = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                s[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName, s);
        }
    }

    // 主要获取日志中其它信息，时长、ip、url...
    @After("execution(* com.wt.controller.*.*(..))")
    public void doAfter(JoinPoint point) throws Exception {
        RequestMapping classAnnotation = null;
        RequestMapping methodAnnotation = null;
        String url = null;
        //获取访问的路径
        if (executionClass != null && executionMethod != null && executionClass != LogAop.class) {
            //获取类注解
            classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                methodAnnotation = (RequestMapping) executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] classValues = classAnnotation.value();
                    String[] methodValues = methodAnnotation.value();
                    url = classValues[0] + methodValues[0];
                }
            }
        }

        //获取访问时长
        long executionTime=(new Date().getTime())-startTime.getTime();

        //获取ip
        String ip = request.getRemoteAddr();

        //获取当前操作者
        SecurityContext context = SecurityContextHolder.getContext();   //获取security上下文对象
        User user = (User) context.getAuthentication().getPrincipal(); //获取当前操作对象
        String username = user.getUsername();   //获取用户名

        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(executionTime);
        sysLog.setVisitTime(startTime);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setMethod("[类名]"+executionClass.getName()+"[方法明]"+executionMethod.getName());

        sysLogService.save(sysLog);
    }
}
