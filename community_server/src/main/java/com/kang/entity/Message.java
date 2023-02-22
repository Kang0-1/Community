package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Message)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**id*/
    private String messageId;
    
    /**发送者id*/
    private Long senderId;
    
    /**接收者id*/
    private Long receiverId;
    
    /**消息内容*/
    private String messageContent;
    
    /**消息种类（系统通知，回复通知，私聊）*/
    private String messageType;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date messageCreatetime;
    
    /**消息状态（已读，未读）*/
    private Integer messageState;
    


}

