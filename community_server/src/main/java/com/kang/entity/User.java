package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**用户id*/
    private Long userId;
    
    /**账号*/
    private String userName;
    
    /**密码*/
    private String userPassword;
    
    /**昵称*/
    private String nickName;
    
    /**用户角色（admin，user）*/
    private String userRole;
    
    /**性别*/
    private String userSex;
    
    /**头像地址*/
    private String userAvatar;
    
    /**个性签名*/
    private String userSign;
    
    /**更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**最近一次的登录时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginDate;
    
    /**用户邮箱*/
    private String userEmail;
    
    /**支付密码*/
    private String payPassword;
    


}

