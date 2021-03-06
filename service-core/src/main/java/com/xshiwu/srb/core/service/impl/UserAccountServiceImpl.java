package com.xshiwu.srb.core.service.impl;

import com.xshiwu.srb.core.pojo.entity.UserAccount;
import com.xshiwu.srb.core.mapper.UserAccountMapper;
import com.xshiwu.srb.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-04-10
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
