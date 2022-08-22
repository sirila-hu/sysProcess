package com.example.mainprocess.test;

import com.example.mainprocess.sysProcess.FinishMethod.ExecutorEnding;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/22 13:16
 * @Description: 钩子函数测试方法
 */
@Component
public class userExecutorEnding implements ExecutorEnding {
    List<String> list = new ArrayList();

    public void add(String str)
    {
        list.add(str);
    }

    @Override
    public void endingExec() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> 开始保存未完成工作 <<<<<<<<<<<<<<<<<<<<<<<<<");
        for (String str : list)
        {
            System.out.println(str);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>     保存完毕     <<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
