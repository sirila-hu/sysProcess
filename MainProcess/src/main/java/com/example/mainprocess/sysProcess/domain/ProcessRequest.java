package com.example.mainprocess.sysProcess.domain;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/17 15:45
 * @Description:
 */
public class ProcessRequest {
    String path;
    String command;
    String data;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
