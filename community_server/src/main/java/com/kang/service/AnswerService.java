package com.kang.service;

import com.kang.entity.Answer;
import com.kang.domain.Result;
import com.kang.entity.vo.CommentVo;

import java.util.List;

/**
 * (Answer)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
public interface AnswerService {

    int saveAnswer(Answer answer);

    List<CommentVo> getAnswerList(Answer answer);
}

