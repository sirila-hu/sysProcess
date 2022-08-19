package com.example.mainprocess.sysProcess.annotation;

import java.lang.annotation.*;

/**
 * @Author: tyy
 * @CreateTime: 2022/8/19 11:17
 * @Description: 映射控制器调用路径
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProcessMapping {
    String value();
}
