package com.kang.controller.Admin.service;

import com.kang.entity.User;

import java.util.Map;

/**
 * @author kang
 * @description
 * @date 2023/4/15 15:32
 */
public interface AdminService {
    Map<Object, Object> goLogin(User user);
}
