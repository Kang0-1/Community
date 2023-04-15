package com.kang.service;

import com.kang.entity.History;
import com.kang.entity.Question;
import com.kang.domain.Result;
import com.kang.entity.vo.QueryQuestionVo;
import com.kang.entity.vo.WorkStateNum;

import java.util.List;

/**
 * (Question)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
public interface QuestionService {

    List<Question> getListByParam(QueryQuestionVo queryQuestionVo);

    int saveQuestion(Question question);

    Question getQuestionInfo(History history);

    WorkStateNum getNumList(Long uid);

    int delQuestion(String qid);

    Question getQuestion(String  qid);

    int acceptAnswer(Question question);
}

