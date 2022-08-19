package com.example.mainprocess.sysProcess;

import com.example.mainprocess.sysProcess.Event.CommandEvent;
import com.example.mainprocess.sysProcess.Event.ResponseEvent;
import com.example.mainprocess.sysProcess.annotation.ProcessMapping;
import com.example.mainprocess.sysProcess.domain.HandlerMethod;
import com.example.mainprocess.sysProcess.domain.ProcessRequest;
import com.example.mainprocess.sysProcess.domain.ProcessResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: admin
 * @CreateTime: 2022/8/17 15:10
 * @Description:
 */
public class dispatcherController {

    Map<String, HandlerMethod> handlerMethods;

    public dispatcherController() {

        this.handlerMethods = this.getHandlerMethods();
    }

    /**
     * 控制器入口
     *
     * @param request
     */
    public void doService(ProcessRequest request) {
        //是否含有执行命令
        if (!StringUtils.isEmpty(request.getCommand())) {
            SpringApplicationHolder.publishEvent(new CommandEvent(request.getCommand()));
        }

        //进入分发逻辑
        this.doDispatcher(request);
    }

    /**
     * 分发逻辑
     *
     * @param request
     */
    private void doDispatcher(ProcessRequest request) {
        ProcessResponse response = null;
        //获取访问路径
        String path = request.getPath();

        //获取请求路径
        String data = request.getData();

        try {
            //获取目标方法
            HandlerMethod targetMethod = handlerMethods.get(path);

            Assert.notNull(targetMethod, "该功能不存在");
            //无映射到的方法
            response = (ProcessResponse) targetMethod.invoke(data);

            if (response == null)
            {
                response = ProcessResponse.success("执行成功");
            }

        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            response = ProcessResponse.error(404, iae.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response = ProcessResponse.error(e.getMessage());
        } finally {
            SpringApplicationHolder.publishEvent(new ResponseEvent(response));
        }

    }

    /**
     * 获取应用控制器方法及其映射路径
     *
     * @return
     */
    private Map getHandlerMethods() {
        Map<String, HandlerMethod> handlerMethods = new HashMap();

        //遍历所有bean来获取被标记的方法
        ApplicationContext applicationContext = SpringApplicationHolder.applicationContext;
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            String prefix = "";
            String suffix = "";

            Object component = applicationContext.getBean(beanName);
            Class clazz = component.getClass();
            Method[] methods = clazz.getMethods();

            //执行类注解判断
            ProcessMapping classProcessMapping = (ProcessMapping) clazz.getAnnotation(ProcessMapping.class);
            if (classProcessMapping != null) {
                prefix = classProcessMapping.value();
            }

            //遍历方法对象
            for (Method method : methods) {
                ProcessMapping methodProcessMapping = method.getAnnotation(ProcessMapping.class);
                if (methodProcessMapping != null) {
                    suffix = methodProcessMapping.value();
                    handlerMethods.put(prefix + suffix, new HandlerMethod(method, component));
                }
            }
        }
        return handlerMethods;
    }

}
