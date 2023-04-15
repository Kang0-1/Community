package com.kang.service.impl;

import cn.hutool.core.util.StrUtil;
import com.kang.entity.History;
import com.kang.entity.Question;
import com.kang.entity.vo.QueryQuestionVo;
import com.kang.entity.vo.WorkStateNum;
import com.kang.mapper.HistoryMapper;
import com.kang.mapper.QuestionMapper;
import com.kang.service.QuestionService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Resource
    private HistoryMapper historyMapper;

    @Override
    public List<Question> getListByParam(QueryQuestionVo queryQuestionVo) {
        return questionMapper.selectByParam(queryQuestionVo);
    }

    @Override
    public int saveQuestion(Question question) {
        if(StrUtil.isNotEmpty(question.getQaId())){
            return questionMapper.updateByPrimaryKeySelective(question);
        }else {
            question.setQaId(UUID.randomUUID().toString());
            question.setQaCreatetime(new Date());
            question.setQaState(1);
            return questionMapper.insertSelective(question);
        }
    }

    @Override
    public Question getQuestionInfo(History history) {
        Question question = questionMapper.selectByPrimaryKey(history.getWorksId());
        // 先查询历史记录中有无该记录，有则更新时间，否则创建新的记录
        List<History> histories = historyMapper.selectByParam(history);
        if(histories.size()>0){
            history=histories.get(0);
            history.setHistoryCreatetime(new Date());
            historyMapper.updateByPrimaryKey(history);
        }else {
            history.setHistoryId(UUID.randomUUID().toString());
            history.setWorksType(2);
            history.setHistoryCreatetime(new Date());
            historyMapper.insertSelective(history);
        }
        return question;
    }

    @Override
    public WorkStateNum getNumList(Long uid) {
        return questionMapper.selectNum(uid);
    }

    @Override
    public int delQuestion(String qid) {
        return questionMapper.deleteByPrimaryKey(qid);
    }

    @Override
    public Question getQuestion(String qid) {
        return questionMapper.selectByPrimaryKey(qid);
    }

    @Override
    public int acceptAnswer(Question question) {
        return questionMapper.updateByPrimaryKeySelective(question);
    }
}

