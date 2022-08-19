package com.example.mainprocess.sysProcess;

import com.example.mainprocess.sysProcess.Event.BaseEvent;
import com.example.mainprocess.sysProcess.Event.CommandEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/17 15:31
 * @Description:
 */
@Component
public class SpringApplicationHolder implements ApplicationContextAware {
    public static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationHolder.applicationContext = applicationContext;
    }

    public static void publishEvent(BaseEvent event)
    {
        applicationContext.publishEvent(event);
    }
}
