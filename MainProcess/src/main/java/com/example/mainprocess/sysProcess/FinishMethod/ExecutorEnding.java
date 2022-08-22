package com.example.mainprocess.sysProcess.FinishMethod;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/22 10:03
 * @Description: 线程收尾接口
 */
public interface ExecutorEnding {
    /**
     * 线程收尾方法,用于程序结束时保存未完成任务
     */
    void endingExec();
}
