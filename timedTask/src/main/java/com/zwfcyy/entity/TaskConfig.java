package com.zwfcyy.entity;

import lombok.Data;

/**
 * @description: 任务配置
 * @author: zwf
 * @create: 2023-09-09 14:31
 **/
@Data
public class TaskConfig {
    private static final long serialVersionUID = 6602540505183675348L;

    private String taskName;
    private String taskGroup;
    private String taskClassName;
    private String cronExpression;
    private int triggerState;
    private int sort;
}
