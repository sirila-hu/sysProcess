package com.example.mainprocess.sysProcess;

import com.example.mainprocess.sysProcess.Listener.BaseListener;
import com.example.mainprocess.sysProcess.Runable.ExceptionMonitorTask;
import com.example.mainprocess.sysProcess.Runable.MessageMonitorTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/17 14:44
 * @Description:
 */
@Component
@Order(1)
public class globalInitialization implements CommandLineRunner
{
    @Value("${sysProcess.init.process}")
    String processName;
    @Override
    public void run(String... args) throws Exception {
        //创建分发控制器(使用构造方法初始化分发控制器)
        dispatcherController dispatcherController = new dispatcherController();
        //创建管道监听线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        //开启子进程
        Resource resource = new ClassPathResource("process"+ File.separator + processName);
        String processPath = resource.getFile().getPath();
        System.out.println("==========开启子进程==========");
        Process process = Runtime.getRuntime().exec("java -jar " + processPath);
        //初始化子进程
        System.out.println("==========初始化子进程==========");

        /*开启管道监听线程*/
        System.out.println("==========开启管道监听==========");
        executorService.execute(new MessageMonitorTask(process.getInputStream()));//信息管道监听
        executorService.execute(new ExceptionMonitorTask(process.getErrorStream()));//错误管道监听

        /*将必须对象注入到事件监听器中*/
        BaseListener.setDispatcherController(dispatcherController);
        BaseListener.setSysExecutor(executorService);
        BaseListener.setProcess(process);

    }
}
