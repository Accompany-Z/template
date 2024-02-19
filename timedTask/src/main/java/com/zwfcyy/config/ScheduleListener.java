package com.zwfcyy.config;

import com.zwfcyy.config.vo.TaskConfig;
import com.zwfcyy.config.vo.TaskEnum;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @description: 根据配置文件中的任务信息来创建和调度Quartz任务
 * @author: zwf
 * @create: 2023-09-09 14:31
 **/
@Slf4j
@Component
@ConfigurationProperties(prefix = "quartz")
public class ScheduleListener implements ApplicationRunner {

    @Autowired
    @Qualifier("scheduler")
    // 注入Scheduler实例
    private Scheduler scheduler;

    // 任务配置信息列表
    List<TaskConfig> tasks = new ArrayList<>();

    /**
     * 在应用程序启动后执行的逻辑
     *
     * @param args
     * @return: void
     * @author: zwf
     * @date: 2023-09-09 14:41:41
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (tasks.size() > 0) {
            // 遍历任务配置信息列表
            for (TaskConfig entity : tasks) {
                // 当定时任务状态为0时，不启动
                if (entity.getTriggerState() == TaskEnum.PAUSED.getCode()) {
                    log.warn("{} Task closed...", entity.getTaskClassName());
                    continue;
                }
                // 创建jobDetail实例，绑定Job实现类,指明job的名称，所在组的名称，以及绑定job类
                Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(entity.getTaskClassName())
                        .newInstance().getClass());
                JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(entity.getTaskName(), entity.getTaskGroup()).build();
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(entity.getCronExpression());
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(entity.getTaskName(), entity.getTaskGroup())
                        .withSchedule(scheduleBuilder).build();
                Map<String, Object> map = new HashMap<>();
                map.put("sort", entity.getSort());
                if (map != null) {
                    trigger.getJobDataMap().putAll(map);
                }
                // 将任务添加到Scheduler中进行调度
                scheduler.scheduleJob(jobDetail, trigger);
                log.info("{} Task loaded...", entity.getTaskClassName());
            }
        } else {
            log.error("The task list is not obtained. Please check whether the configuration file is correct...");
        }
    }

    /**
     * 获取任务配置信息列表
     *
     * @return: java.util.List<com.hysoft.entity.TaskConfig>
     * @author: zwf
     * @date: 2023-09-09 14:42:25
     */
    public List<TaskConfig> getJobs() {
        return tasks;
    }

    /**
     * 设置任务配置信息列表
     *
     * @param tasks
     * @return: void
     * @author: zwf
     * @date: 2023-09-09 14:42:31
     */
    public void setJobs(List<TaskConfig> tasks) {
        this.tasks = tasks;
    }
}
