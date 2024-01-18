package com.zwfcyy.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;


/**
 * @author: zwf
 * @since: 2023-12-05 16:17:40
 * @description: 定时任务测试2
 */
@Slf4j
@Component
public class ExamplesDemo2Task implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("This is the scheduled task for the second test...");
    }
}
