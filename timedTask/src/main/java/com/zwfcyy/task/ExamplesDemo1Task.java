package com.zwfcyy.task;

import com.zwfcyy.mapper.xml.TimedTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author: zwf
 * @since: 2023-12-05 16:17:40
 * @description: 定时任务测试1
 */
@Slf4j
@Component
public class ExamplesDemo1Task implements Job {

    @Resource
    private TimedTaskMapper mapper;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info("This is a timed task for the first test...");
        log.info("查询当前时间：{}", mapper.getDateTime());

    }
}
