package com.example.mainprocess.sysProcess.domain;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: admin
 * @CreateTime: 2022/8/19 14:14
 * @Description:
 */
public class HandlerMethod {
    //方法对象
    private Method method;

    //方法对应的类实例
    private Object classExample;

    public HandlerMethod() {
    }

    public HandlerMethod(Method method, Object classExample) {
        this.method = method;
        this.classExample = classExample;
    }

    public Object invoke(String data) throws Exception {
        Map map = JSON.parseObject(data);
        return method.invoke(classExample, map);
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getClassExample() {
        return classExample;
    }

    public void setClassExample(Object classExample) {
        this.classExample = classExample;
    }
}
