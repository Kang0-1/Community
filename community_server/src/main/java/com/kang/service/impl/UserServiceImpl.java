package com.kang.service.impl;

import com.kang.entity.User;
import com.kang.mapper.UserMapper;
import com.kang.service.UserService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service("userService")
public class UserServiceImpl implements UserService {
        @Resource
        private UserMapper userMapper;

}

