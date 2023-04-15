package com.kang.controller.Admin.service.impl;

import com.kang.controller.Admin.service.AdminService;
import com.kang.entity.User;
import com.kang.mapper.*;
import com.kang.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author kang
 * @description
 * @date 2023/4/15 15:32
 */
@Service
public class AdminServiceImpl implements AdminService {


    @Resource
    UserMapper userMapper;
    @Resource
    JwtUtil jwtUtil;
    @Resource
    CategoryMapper categoryMapper;
    @Resource
    CommentMapper commentMapper;
    @Resource
    AnswerMapper answerMapper;
    @Resource
    VideoMapper videoMapper;
    @Resource
    ArticleMapper articleMapper;
    @Resource
    QuestionMapper questionMapper;
    @Resource
    ResourceMapper resourceMapper;
    @Resource
    InformMapper informMapper;
    @Resource
    AttentionMapper attentionMapper;

    @Override
    public Map<Object, Object> goLogin(User user) {

        return null;
    }
}
