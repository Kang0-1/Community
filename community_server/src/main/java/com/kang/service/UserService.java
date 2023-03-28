package com.kang.service;

import com.kang.entity.User;
import com.kang.domain.Result;
import com.kang.entity.vo.LoginVo;

import java.util.Map;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
public interface UserService {

    /**
     * 通过 user 的 username||email||id 查找 User
     * @description Service
     * @param user user
     * @return User
     */
    User selectUserByParam(User user);

    Result login(LoginVo loginVo);

    Map<Object, Object> getImgCode();

    Map<Object, Object> getEmailCode(String email);
}

