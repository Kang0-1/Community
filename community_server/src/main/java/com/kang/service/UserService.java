package com.kang.service;

import com.kang.entity.User;
import com.kang.domain.Result;
import com.kang.entity.vo.LoginVo;
import com.kang.entity.vo.PassVo;
import com.kang.entity.vo.PayVo;
import com.kang.entity.vo.SomeNum;

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

    /**
     * 登录接口，实现邮箱注册登录或密码登录
     * @param loginVo 登录信息
     * @return Map封装的用户信息和token
     */
    Result login(LoginVo loginVo);

    /**
     * 图片验证码
     * @return Map封装的 验证码图片url 和 redis中的key值
     */
    Map<Object, Object> getImgCode();

    /**
     * 发送验证码给指定邮箱，用于邮箱注册,登录
     * @param email 邮箱
     * @return redis中的随机key值
     */
    Map<Object, Object> getEmailCode(String email);

    /**
     * 查询用户的关注数，粉丝数和收获点赞数
     */
    SomeNum getSomeNum(Long id);

    /**
     * 支付接口
     */
    int goPay(PayVo payVo);

    int saveUser(User user);

    Double getBalance(Long id);

    int change(PassVo passVo);
}

