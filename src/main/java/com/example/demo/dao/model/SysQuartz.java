package com.example.demo.dao.model;

import lombok.Data;

/**
 * <p>
 * 定时任务信息表
 * </p>
 *
 * @author lizhou
 * @since 2020-07-21
 */
@Data
public class SysQuartz {


    private Long id;


    private String className;


    private String cronExpression;

    private String param;

    private String descript;


    private Integer quartzStatus;


    private Integer status;


    private Integer delFlag;


    private Long createUser;

    private String createTime;


}
