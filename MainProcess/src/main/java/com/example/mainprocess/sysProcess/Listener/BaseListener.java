package com.example.mainprocess.sysProcess.Listener;

import com.example.mainprocess.sysProcess.dispatcherController;

import java.util.concurrent.ExecutorService;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/17 15:12
 * @Description:
 */
public class BaseListener {
    //系统线程(管道监听)
    private static ExecutorService sysExecutor;
    //子进程
    private static Process process;
    //分发控制器
    private static dispatcherController dispatcherController;


    public static ExecutorService getSysExecutor() {
        return sysExecutor;
    }

    public static void setSysExecutor(ExecutorService sysExecutor) {
        BaseListener.sysExecutor = sysExecutor;
    }

    public static Process getProcess() {
        return process;
    }

    public static void setProcess(Process process) {
        BaseListener.process = process;
    }

    public static com.example.mainprocess.sysProcess.dispatcherController getDispatcherController() {
        return dispatcherController;
    }

    public static void setDispatcherController(com.example.mainprocess.sysProcess.dispatcherController dispatcherController) {
        BaseListener.dispatcherController = dispatcherController;
    }
}
