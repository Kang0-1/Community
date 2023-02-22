package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Answer)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**id*/
    private String answerId;
    
    /**回答内容*/
    private String answerContent;
    
    /**回答时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date answerCreatetime;
    
    /**回答者id*/
    private Long authorId;
    
    /**回答者名字*/
    private String authorName;
    
    /**提问id*/
    private String questionId;
    
    /**父id（这里只能提问者和回答者两人进行问答，也就是说其他所有人只能与提问者进行互动）*/
    private String parentId;
    
    /**回答者头像*/
    private String authorAvatar;
    


}

