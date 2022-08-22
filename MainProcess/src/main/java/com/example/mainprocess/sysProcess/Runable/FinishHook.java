package com.example.mainprocess.sysProcess.Runable;

import com.alibaba.fastjson.JSON;
import com.example.mainprocess.sysProcess.Event.ResponseEvent;
import com.example.mainprocess.sysProcess.SpringApplicationHolder;
import com.example.mainprocess.sysProcess.domain.ProcessResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/22 10:45
 * @Description: 收尾钩子函数
 */
public class FinishHook implements Runnable{
    Method method;
    Object sourceObject;

    public FinishHook(Method method, Object sourceObject) {
        this.method = method;
        this.sourceObject = sourceObject;
    }

    @Override
    public void run() {
        try {
            method.invoke(sourceObject, null);
        } catch (Exception e) {
            e.printStackTrace();
            SpringApplicationHolder.publishEvent(e);
        }
    }
}
