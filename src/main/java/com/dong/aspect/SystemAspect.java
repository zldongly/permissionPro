package com.dong.aspect;

import com.dong.domain.Systemlog;
import com.dong.mapper.SystemlogMapper;
import com.dong.util.RequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by dongly on 2019/7/25
 *
 * 切面   添加日志（ip、时间、方法、参数）到数据表
 */

public class SystemAspect {

    @Autowired
    private SystemlogMapper systemlogMapper;

    public void writeLog(JoinPoint joinPoint) throws JsonProcessingException {
        //System.out.println("write log");
        Systemlog systemlog = new Systemlog();

        systemlog.setOptime(new Date());

        HttpServletRequest request = RequestUtil.getRequest();
        if (request != null) {
            String ip = request.getRemoteAddr();
            systemlog.setIp(ip);
        }

        String name = joinPoint.getTarget().getClass().getName();   // 方法路径
        String signature = joinPoint.getSignature().getName();      // 方法名称
        systemlog.setFunc(name+":"+signature);

        String params = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
        systemlog.setParams(params);

        System.out.println(systemlog);
        systemlogMapper.insert(systemlog);
    }
}
