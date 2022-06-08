package com.example.demo.service.impl;


import com.example.demo.dao.model.SysQuartz;
import com.oracle.deploy.update.UpdateCheck;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 定时任务信息表 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2020-07-21
 */
@Slf4j
@Service
public class SysQuartzServiceImpl {

    @Autowired
    private Scheduler scheduler;

    /**
     * 添加定时任务
     */
    public boolean add(SysQuartz sysQuartz) {

        // 启动
        if (0 == sysQuartz.getQuartzStatus()) {
            this.schedulerAdd(sysQuartz.getClassName().trim(), sysQuartz.getCronExpression().trim(), sysQuartz.getParam());
        }
        return true;
    }

    /**
     * 添加定时任务
     *
     * @param className
     * @param cronExpression
     * @param param
     */
    public void schedulerAdd(String className, String cronExpression, String param) {
        try {
            // 启动调度器
            scheduler.start();
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(className).getClass()).withIdentity(className).usingJobData("param", param).build();
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(className).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 删除定时任务
     *
     * @param className
     */
    public void schedulerDelete(String className) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(className));
            scheduler.unscheduleJob(TriggerKey.triggerKey(className));
            scheduler.deleteJob(JobKey.jobKey(className));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private static Job getClass(String className) throws Exception {
        Class<?> class1 = Class.forName(className);
        return (Job) class1.newInstance();
    }
}
