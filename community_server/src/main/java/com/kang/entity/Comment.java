package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Comment)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**评论id*/
    private String commentId;
    
    /**发布者id*/
    private Long authorId;
    
    /**发布者名字*/
    private String authorName;
    
    /**评论内容*/
    private String commentContent;
    
    /**作品id*/
    private String worksId;
    
    /**父评论id*/
    private String parentId;
    
    /**被评论人的id*/
    private Long replierId;
    
    /**被评论人的名字*/
    private String replierName;
    
    /**评论时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentCreatetime;
    
    /**评论状态*/
    private String commentState;
    
    /**发布者头像*/
    private String authorAvatar;
    
    /**被评论人头像*/
    private String replierAvatar;
    


}

