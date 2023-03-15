package com.kang.service.impl;

import com.kang.entity.Comment;
import com.kang.mapper.CommentMapper;
import com.kang.service.CommentService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:58
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
        @Resource
        private CommentMapper commentMapper;

}

