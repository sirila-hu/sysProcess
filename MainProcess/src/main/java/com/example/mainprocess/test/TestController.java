package com.example.mainprocess.test;

import com.example.mainprocess.sysProcess.annotation.ProcessMapping;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: admin
 * @CreateTime: 2022/8/19 13:04
 * @Description:
 */
@Component
public class TestController {
    @Autowired
    userExecutorEnding userExecutorEnding;

    @ProcessMapping("/confirm/list")
    public void testMethod(Map data) throws JSONException {
        userExecutorEnding.add((String) data.get("user"));
//        System.out.println(data.get("user"));
    }
}
