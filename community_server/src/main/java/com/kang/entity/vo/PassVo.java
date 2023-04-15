package com.kang.entity.vo;

import lombok.Data;

@Data
public class PassVo {

    Long uid;

    String oldPassword;

    String newPassword;

    /**
     * 0:设置登录密码  1:修改登录密码    2:设置支付密码  3:修改支付密码
     */
    int type;

}
