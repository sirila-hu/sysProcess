package com.example.mainprocess.sysProcess.Runable;

import com.alibaba.fastjson.JSON;
import com.example.mainprocess.sysProcess.Event.CommandEvent;
import com.example.mainprocess.sysProcess.SpringApplicationHolder;
import com.example.mainprocess.sysProcess.domain.ProcessRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/17 16:04
 * @Description: 错误管道监听
 */
public class ExceptionMonitorTask implements Runnable{
    private BufferedReader ExceptionReader;
    private String line;

    public ExceptionMonitorTask(InputStream inputStream) {
        ExceptionReader = IOUtils.toBufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void run() {
        try {
            while (true) {
                line = ExceptionReader.readLine();
                if (line!=null && line.startsWith("erro:")) {
                    String message =line.substring(line.indexOf(":") + 1);
                    System.err.println("子进程报错: " + message);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
