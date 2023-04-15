package com.kang.mapper;


import com.kang.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Answer)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Mapper
public interface AnswerMapper {

    int insert(Answer answer);

    List<Answer> selectByParam(Answer answer);
}


