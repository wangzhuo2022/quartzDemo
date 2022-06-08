package com.example.demo.service.impl;

import com.example.demo.quartz.job.task;
import com.example.demo.service.SysQuartzService;
import org.junit.jupiter.api.Test;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysQuartzServiceImplTest {
    @Autowired
    private SysQuartzService sysQuartzService;
    com.example.demo.quartz.job.task task = new task();

    @Test
    void contextLoads() {
        sysQuartzService.add();

    }
}