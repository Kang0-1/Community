package com.kang.mapper;

import com.kang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Mapper
public interface UserMapper {

    /**
     * 通过 user 的 username||email||id 查找 User
     * @description Mapper
     * @param user user
     * @return User
     */
    User selectUserByParam(User user);

    void updateByPrimaryKey(User user);

    int insertSelective(User user);
}


