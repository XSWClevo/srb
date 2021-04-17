package com.xshiwu.srb.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 文件上传微服务
 * @author xsw
 * @version 1.0
 * @date 2021/4/17 18:47
 */
@SpringBootApplication
@ComponentScan({"com.xshiwu.srb", "com.xshiwu.common"})
public class ServiceOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class, args);
    }

}