package com.xshiwu.srb.sms;

import com.xshiwu.srb.sms.util.SmsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xsw
 * @version 1.0
 * @date 2021/4/17 14:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceSmsApplicationTest {

    @Test
    public void test1(){
        System.out.println("SmsProperties.KEY_ID = " + SmsProperties.KEY_ID);
        System.out.println("SmsProperties.KEY_SECRET = " + SmsProperties.KEY_SECRET);
        System.out.println("SmsProperties.REGION_Id = " + SmsProperties.REGION_Id);
        System.out.println("SmsProperties.SIGN_NAME = " + SmsProperties.SIGN_NAME);
        System.out.println("SmsProperties.TEMPLATE_CODE = " + SmsProperties.TEMPLATE_CODE);
    }
}
