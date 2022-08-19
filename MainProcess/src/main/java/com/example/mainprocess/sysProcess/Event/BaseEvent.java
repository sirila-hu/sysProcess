package com.example.mainprocess.sysProcess.Event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/17 15:12
 * @Description:
 */
public abstract class BaseEvent extends ApplicationEvent {
    public BaseEvent(Object source) {
        super(source);
    }
}
