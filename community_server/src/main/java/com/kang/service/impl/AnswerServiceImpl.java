package com.kang.service.impl;

import com.kang.entity.Answer;
import com.kang.mapper.AnswerMapper;
import com.kang.service.AnswerService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Answer)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Service
public class AnswerServiceImpl implements AnswerService {
        @Resource
        private AnswerMapper answerMapper;


}

