package com.example.mainprocess.sysProcess.Listener;

import com.alibaba.fastjson.JSON;
import com.example.mainprocess.sysProcess.Const.sysConst;
import com.example.mainprocess.sysProcess.Event.CommandEvent;
import com.example.mainprocess.sysProcess.Event.RequestDetectedEvent;
import com.example.mainprocess.sysProcess.Event.ResponseEvent;
import com.example.mainprocess.sysProcess.domain.ProcessRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.SecureRandom;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/18 9:51
 * @Description: 主监听器
 */
@Component
public class MainListener extends BaseListener {

    /**
     * 请求发现处理
     * @param requestDetectedEvent
     */
    @EventListener(RequestDetectedEvent.class)
    public void requestDetectedListener(RequestDetectedEvent requestDetectedEvent)
    {
        //将请求转发至分发控制器
        getDispatcherController().doService((ProcessRequest) requestDetectedEvent.getSource());
    }

    /**
     * 关闭事件处理
     *
     * @param commandEvent
     */
    @EventListener(CommandEvent.class)
    public void existListener(CommandEvent commandEvent) {
        String command = (String) commandEvent.getSource();
        switch (command) {
            case sysConst.REQUEST_COMMAND_EXIT:
                quitSys();
                break;
        }
    }

    /**
     * 发送响应
     * @param responseEvent
     */
    @EventListener(ResponseEvent.class)
    public void ResponseListener(ResponseEvent responseEvent)
    {
        String responseJSON = JSON.toJSONString(responseEvent.getSource());

        //发送给子进程
        PrintWriter out = new PrintWriter(getProcess().getOutputStream());
        out.println(responseJSON);
    }

    /**
     * 退出程序
     */
    private void quitSys() {
        //关闭子进程
        Process process = getProcess();
        if (process.isAlive()) {
            process.destroy();
        }
        //调用应用线程收尾方法

        System.exit(0);
    }

}
