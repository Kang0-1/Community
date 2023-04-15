package com.kang.service.impl;

import cn.hutool.core.util.StrUtil;
import com.kang.entity.Answer;
import com.kang.entity.Comment;
import com.kang.entity.vo.CommentVo;
import com.kang.entity.vo.SimpleUser;
import com.kang.mapper.AnswerMapper;
import com.kang.service.AnswerService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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


    @Override
    public int saveAnswer(Answer answer) {
        answer.setAnswerId(UUID.randomUUID().toString());
        answer.setAnswerCreatetime(new Date());
        return answerMapper.insert(answer);
    }

    @Override
    public List<CommentVo> getAnswerList(Answer answer) {
        String acceptId = answer.getAcceptId();
        List<Answer> baseList= answerMapper.selectByParam(answer);
        List<CommentVo> commentVoList = PoListToVoList(baseList);
        List<CommentVo> result = commentVoList.stream()
                .filter(commentVo -> commentVo.getParentId().equals("-1"))
                .map(commentVo -> {
                    commentVo.setChildrenList(getChildren(commentVo, commentVoList));
                    return commentVo;
                })
                .sorted(Comparator.comparing(CommentVo::getCreateDate).reversed())
                .collect(Collectors.toList());
        // if acceptId不为空，则将踩奶的答案放到第一个位置
        if(StrUtil.isNotEmpty(answer.getAcceptId())){
            int index=0;
            for(int i=0;i<result.size();i++){
                if(result.get(i).getId().equals(acceptId)){
                    index=i;
                    break;
                }
            }
            Collections.swap(result,index,0);
        }
        return result;
    }

    private List<CommentVo> getChildren(CommentVo root, List<CommentVo> all) {
        List<CommentVo> children = all.stream().
                filter(commentVo -> commentVo.getParentId().equals(root.getId()) )
                .map(commentVo -> {
                    commentVo.setChildrenList(getChildren(commentVo, all));
                    return commentVo;
                })
                .sorted(Comparator.comparing(CommentVo::getCreateDate).reversed()) // 时间倒序
                .collect(Collectors.toList());
        return children;
    }

    private List<CommentVo> PoListToVoList(List<Answer> list) {
        List<CommentVo> voList = new ArrayList<>();
        for (Answer answer : list) {
            CommentVo commentVo = new CommentVo();
            SimpleUser commentUser = new SimpleUser();

            commentVo.setId(answer.getAnswerId());
            commentVo.setParentId(answer.getParentId());
            commentVo.setContent(answer.getAnswerContent());
            commentVo.setCreateDate(answer.getAnswerCreatetime());
            // 评论发布者
            commentUser.setId(answer.getAuthorId());
            commentUser.setNickName(answer.getAuthorName());
            commentUser.setAvatar(answer.getAuthorAvatar());
            commentVo.setCommentUser(commentUser);
            // 评论接受者
            voList.add(commentVo);
        }
        return voList;
    }
}

