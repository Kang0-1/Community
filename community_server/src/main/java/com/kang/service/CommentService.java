package com.kang.service;

import com.kang.entity.Comment;
import com.kang.entity.vo.CommentVo;
import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:02:58
 */
public interface CommentService {

    List<CommentVo> getCommentsByParam(Comment comment);

    int saveComment(Comment comment);
}

