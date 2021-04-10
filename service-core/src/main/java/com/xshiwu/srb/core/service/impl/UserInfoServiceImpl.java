package com.xshiwu.srb.core.service.impl;

import com.xshiwu.srb.core.pojo.entity.UserInfo;
import com.xshiwu.srb.core.mapper.UserInfoMapper;
import com.xshiwu.srb.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-04-10
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
