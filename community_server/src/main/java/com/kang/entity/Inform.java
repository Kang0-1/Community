package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Inform)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inform implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**通知id*/
    private String informId;
    
    /**通知内容*/
    private String informContent;
    
    /**接受者id*/
    private Long informReceiver;
    
    /**时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date informCreatetime;
    
    /**作品id*/
    private String workId;
    
    /**作品种类*/
    private Integer workType;
    
    /**通知状态（0：未读，1：已读）*/
    private Integer informState;
    
    /**类型：系统通知、动态通知*/
    private Integer informType;

    /**
     * 其他参数
     */
    private String authorName;

    private String workName;

    private int applyState;
    


}

