package com.kang.controller;


import com.kang.domain.Result;
import com.kang.entity.Comment;
import com.kang.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* (Comment)表控制层
*
* @author makejava
* @since 2023-02-26 15:02:58
*/
@RestController
@RequestMapping("comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/list")
    public Result list(@RequestBody Comment comment){
        return Result.success(commentService.getCommentsByParam(comment));
    }

    @PostMapping("/save/{type}")
    public Result save(@RequestBody Comment comment,@PathVariable int type){
        if(type==0){
            comment.setParentId("-1");
        }
        int i=commentService.saveComment(comment);
        if(i==1){
            return Result.success("评论成功!");
        }else {
            return Result.error("评论失败!");
        }
    }

}


