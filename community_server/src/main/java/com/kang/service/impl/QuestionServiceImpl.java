package com.kang.service.impl;

import com.kang.entity.Question;
import com.kang.mapper.QuestionMapper;
import com.kang.service.QuestionService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Question)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
        @Resource
        private QuestionMapper questionMapper;

}

