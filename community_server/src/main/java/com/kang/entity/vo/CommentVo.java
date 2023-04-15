package com.kang.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVo {
    private String id;

    private String parentId;

    private SimpleUser commentUser;

    private SimpleUser targetUser;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private List<CommentVo> childrenList;
}
