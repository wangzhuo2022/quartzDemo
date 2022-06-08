package com.example.demo.config;

import com.example.demo.dao.model.SysQuartz;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SysQuartzConfig {

    @Bean
    public SysQuartz getSysQuartzConfig() {
        SysQuartz sysQuartz = new SysQuartz();
        sysQuartz.setQuartzStatus(0);
        sysQuartz.setClassName("定时的告警任务！");
        sysQuartz.setParam("111！");
        sysQuartz.setCronExpression("0/2 * * * * ?");
        return sysQuartz;
    }
}
