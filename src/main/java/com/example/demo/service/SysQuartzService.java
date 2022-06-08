package com.example.demo.service;

public interface SysQuartzService {

    boolean add();

    void schedulerDelete(String className);

    void schedulerAdd(String className, String cronExpression, String param);
}
