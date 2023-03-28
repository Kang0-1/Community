package com.kang.entity.vo;

import lombok.Data;

/**
 * @author kang
 * @description TODO
 * @date 2023/3/25 16:58
 */
@Data
public class LoginVo {

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 验证码
     */
    private String code;

    /**
     * key
     */
    private String key;
}
