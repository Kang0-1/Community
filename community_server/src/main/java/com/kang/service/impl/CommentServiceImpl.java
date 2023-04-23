package com.kang.service.impl;

import com.kang.entity.Comment;
import com.kang.entity.Inform;
import com.kang.entity.Video;
import com.kang.entity.vo.CommentVo;
import com.kang.entity.vo.SimpleUser;
import com.kang.mapper.CommentMapper;
import com.kang.mapper.InformMapper;
import com.kang.mapper.VideoMapper;
import com.kang.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private InformMapper informMapper;

    @Resource
    private VideoMapper videoMapper;

    @Override
    public List<CommentVo> getCommentsByParam(Comment comment) {
        // 1.查出所有列表评论 并转换成VoList
        List<Comment> baseList = commentMapper.selectByParam(comment);
        List<CommentVo> commentVos = PoListToVoList(baseList);
        // 2.通过父评论id=-1 找出一级评论
        List<CommentVo> result = commentVos.stream().filter(commentVo -> commentVo.getParentId().equals("-1"))
                .map(commentVo -> {
                    commentVo.setChildrenList(getChildren(commentVo, commentVos));
                    return commentVo;
                })
                .sorted(Comparator.comparing(CommentVo::getCreateDate).reversed())  // 时间降序
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public int saveComment(Comment comment,int type) {
        comment.setCommentId(UUID.randomUUID().toString());
        comment.setCommentCreatetime(new Date());
        // 后续进行 系统通知
        if(type==1){
            Inform inform=new Inform();
            inform.setInformId(UUID.randomUUID().toString());
            inform.setInformCreatetime(new Date());
            inform.setInformReceiver(comment.getReplierId());
            inform.setWorkId(comment.getWorksId());
            Video video = videoMapper.selectByPrimaryKey(comment.getWorksId());
            inform.setWorkType(null==video?1:0);
            // 动态通知
            inform.setInformType(1);
            // 未读
            inform.setInformState(0);
            inform.setInformContent("您的评论收到回复《"+comment.getCommentContent()+"》 来自:@"+comment.getAuthorName());
            informMapper.insertSelective(inform);
        }
        return commentMapper.insertSelective(comment);
    }

    private List<CommentVo> getChildren(CommentVo root, List<CommentVo> all) {
        List<CommentVo> children = all.stream().
                filter(commentVo -> commentVo.getParentId().equals(root.getId()) && commentVo.getTargetUser().getId().equals(root.getCommentUser().getId()))
                .map(commentVo -> {
                    commentVo.setChildrenList(getChildren(commentVo, all));
                    return commentVo;
                })
                .sorted(Comparator.comparing(CommentVo::getCreateDate).reversed()) // 时间倒序
                .collect(Collectors.toList());
        return children;
    }

    private List<CommentVo> PoListToVoList(List<Comment> list) {
        List<CommentVo> voList = new ArrayList<>();
        for (Comment comment : list) {
            CommentVo commentVo = new CommentVo();
            SimpleUser commentUser = new SimpleUser();
            SimpleUser targetUser = new SimpleUser();

            commentVo.setId(comment.getCommentId());
            commentVo.setParentId(comment.getParentId());
            commentVo.setContent(comment.getCommentContent());
            commentVo.setCreateDate(comment.getCommentCreatetime());
            // 评论发布者
            commentUser.setId(comment.getAuthorId());
            commentUser.setAvatar(comment.getAuthorAvatar());
            commentUser.setNickName(comment.getAuthorName());
            commentVo.setCommentUser(commentUser);
            // 评论接受者
            targetUser.setId(comment.getReplierId());
            targetUser.setAvatar(comment.getReplierAvatar());
            targetUser.setNickName(comment.getReplierName());
            commentVo.setCommentUser(commentUser);
            commentVo.setTargetUser(targetUser);
            voList.add(commentVo);
        }
        return voList;
    }
}

