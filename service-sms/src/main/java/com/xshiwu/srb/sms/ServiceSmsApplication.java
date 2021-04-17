package com.xshiwu.srb.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xsw
 * @version 1.0
 * @date 2021/4/17 14:18
 */
@SpringBootApplication
@ComponentScan({"com.xshiwu.srb", "com.xshiwu.common"})
public class ServiceSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class, args);
    }

}
