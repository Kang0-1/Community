package com.kang.mapper;

import com.kang.entity.Question;
import com.kang.entity.User;
import com.kang.entity.vo.QueryQuestionVo;
import com.kang.entity.vo.WorkStateNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Question)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Mapper
public interface QuestionMapper {

    Question selectByPrimaryKey(String questionId);

    List<Question> selectByParam(QueryQuestionVo queryQuestionVo);

    int updateByPrimaryKeySelective(Question question);

    int insertSelective(Question question);

    WorkStateNum selectNum(Long uid);

    int deleteByPrimaryKey(String qid);

    int updateUserInfoByUserId(User user);
}


