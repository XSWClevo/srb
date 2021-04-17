package com.xshiwu.srb.sms.service;

import java.util.Map;

/**
 * @author xsw
 * @version 1.0
 * @date 2021/4/17 15:45
 */
public interface SmsService {

    void send(String mobile, String templateCode, Map<String,Object> param);
}
