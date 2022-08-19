package com.example.mainprocess.sysProcess.domain;

import java.util.HashMap;

/**
 * @Author: admin
 * @CreateTime: 2022/8/19 11:28
 * @Description:
 */
public class ProcessResponse extends HashMap {
    public ProcessResponse() {
    }

    public ProcessResponse(int code, String msg) {
        super.put("code", code);
        super.put("message", msg);
    }

    public ProcessResponse(int code, String msg, Object data) {
        super.put("code", code);
        super.put("message", msg);

        if (data != null) {
            super.put("data", data);
        }
    }

    public static ProcessResponse success(String msg) {
        return success(msg, null);
    }

    public static ProcessResponse success(Object data) {
        return success("操作成功", data);
    }

    public static ProcessResponse success(String msg, Object data) {
        return new ProcessResponse(200, msg, data);
    }

    public static ProcessResponse error( String msg){
        return error(500, msg);
    }

    public static ProcessResponse error(int code, String msg){
        return new ProcessResponse(code, msg);
    }

}
