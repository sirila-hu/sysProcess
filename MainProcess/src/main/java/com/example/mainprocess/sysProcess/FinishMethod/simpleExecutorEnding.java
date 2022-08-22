package com.example.mainprocess.sysProcess.FinishMethod;

import org.springframework.stereotype.Component;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/22 10:07
 * @Description: 线程收尾的默认实现
 */
@Component
public class simpleExecutorEnding implements ExecutorEnding{
    @Override
    public void endingExec() {
        System.out.println("==========应用线程结束==========");
    }
}
