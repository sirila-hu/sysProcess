package com.example.mainprocess.sysProcess.Runable;

import com.alibaba.fastjson.JSON;
import com.example.mainprocess.sysProcess.Event.CommandEvent;
import com.example.mainprocess.sysProcess.Event.RequestDetectedEvent;
import com.example.mainprocess.sysProcess.SpringApplicationHolder;
import com.example.mainprocess.sysProcess.domain.ProcessRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/17 16:02
 * @Description: 信息管道监听任务
 */
public class MessageMonitorTask implements Runnable {
    private BufferedReader messageReader;
    private String line;

    public MessageMonitorTask(InputStream inputStream) {
        this.messageReader = IOUtils.toBufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void run() {
        try {
            while (true) {
                line = messageReader.readLine();
                if (line!=null && line.startsWith("requst:")) {
                    String message =line.substring(line.indexOf(":") + 1);
                    System.out.println(message);
                    //解析请求
                    ProcessRequest request = JSON.parseObject(message, ProcessRequest.class);

                    //将请求转发至dispatcherController
                    SpringApplicationHolder.applicationContext.publishEvent(new RequestDetectedEvent(request));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
