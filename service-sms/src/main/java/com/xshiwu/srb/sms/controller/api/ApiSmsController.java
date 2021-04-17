package com.xshiwu.srb.sms.controller.api;

import com.xshiwu.common.exception.Assert;
import com.xshiwu.common.result.R;
import com.xshiwu.common.result.ResponseEnum;
import com.xshiwu.common.util.RandomUtils;
import com.xshiwu.common.util.RegexValidateUtils;
import com.xshiwu.srb.sms.service.SmsService;
import com.xshiwu.srb.sms.util.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author xsw
 * @version 1.0
 * @date 2021/4/17 16:58
 */
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SmsService smsService;

    @GetMapping("api/{mobile}")
    public R sengSms(@ApiParam(value = "手机号", required = true) @PathVariable String mobile){
        System.err.println(mobile);
        // 验证手机号参数是否为空
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        // 验证手机号是否合法
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile),ResponseEnum.MOBILE_ERROR);
        HashMap<String, Object> map = new HashMap<>();
        // 随机生成四位验证码
        String code = RandomUtils.getFourBitRandom();
        map.put("code", code);
        smsService.send(mobile, SmsProperties.TEMPLATE_CODE, map);
        // 将手机号和验证码存入redis,并且设置5s过期
        redisTemplate.opsForValue().set("srb:sms:code:" + mobile, code, 5, TimeUnit.MINUTES);
        return R.ok().message("短信发送成功");
    }
}
