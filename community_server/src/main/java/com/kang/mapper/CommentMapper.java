package com.kang.mapper;

import com.kang.entity.Comment;
import com.kang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:02:58
 */
@Mapper
public interface CommentMapper {

    List<Comment> selectByParam(Comment comment);

    int insertSelective(Comment comment);

    int updateByPrimaryKeySelective(Comment comment);

    int updateUserInfoByUserId(User user);
}


