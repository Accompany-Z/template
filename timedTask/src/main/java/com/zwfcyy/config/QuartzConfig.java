package com.zwfcyy.config;

import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;


/**
 * @description: 配置Quartz调度器（Scheduler）并将其纳入Spring容器中管理
 * @author: zwf
 * @create: 2023-09-09 14:31
 **/
@Configuration
public class QuartzConfig {

    @Autowired
    private JobFactory jobFactory;


    /**
     * 配置SchedulerFactoryBean，并将其交由Spring容器管理
     *
     * @return: org.springframework.scheduling.quartz.SchedulerFactoryBean
     * @author: zwf
     * @date: 2023-09-09 14:37:42
     */
    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 设置自行启动
        //schedulerFactoryBean.setAutoStartup(false);
        // 设置JobFactory，用于在需要时创建Job
        schedulerFactoryBean.setJobFactory(jobFactory);
        // 覆盖已存在的Jobs
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 延时启动
        schedulerFactoryBean.setStartupDelay(5);
        return schedulerFactoryBean;
    }

    /**
     * 配置Scheduler，并将其交由Spring容器管理
     *
     * @return: org.quartz.Scheduler
     * @author: zwf
     * @date: 2023-09-09 14:37:33
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() throws IOException {
        // 从SchedulerFactoryBean中获取Scheduler
        return schedulerFactoryBean().getScheduler();
    }
}
