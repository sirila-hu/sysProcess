package com.example.mainprocess.sysProcess;

import com.alibaba.fastjson.JSON;
import com.example.mainprocess.sysProcess.Event.BaseEvent;
import com.example.mainprocess.sysProcess.Event.ResponseEvent;
import com.example.mainprocess.sysProcess.domain.ProcessResponse;
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

    /**
     * 发送错误响应
     * @param e
     */
    public static void publishEvent(Exception e)
    {
        String response = JSON.toJSONString(ProcessResponse.error(e.getMessage()));
        SpringApplicationHolder.publishEvent(new ResponseEvent(response));
    }
}
