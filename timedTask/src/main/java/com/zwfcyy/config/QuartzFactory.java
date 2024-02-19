package com.zwfcyy.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * /**
 *
 * @description: Quartz 框架创建 Job 实例时，通过 Spring 的依赖注入机制自动装配所需的依赖
 * @author: zwf
 * @create: 2023-09-09 14:31
 **/
@Component
@EnableScheduling
public class QuartzFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    /**
     * 覆盖父类的方法，用于创建Job实例
     *
     * @param bundle
     * @return: java.lang.Object
     * @author: zwf
     * @date: 2023-09-09 14:39:30
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的创建Job实例方法，获取默认的Job实例
        Object jobInstance = super.createJobInstance(bundle);
        // 使用AutowireCapableBeanFactory完成对IOC外Bean的注入操作
        capableBeanFactory.autowireBean(jobInstance);
        // 返回经过自动装配后的Job实例
        return jobInstance;
    }


}
