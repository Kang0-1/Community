package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Resource)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**id*/
    private String resourceId;
    
    /**作者id*/
    private Long authorId;
    
    /**标题*/
    private String resourceTitle;
    
    /**简介*/
    private String resourceSummary;
    
    /**地址*/
    private String resourceUrl;
    
    /**所需硬币*/
    private Integer resourceCoin;
    
    /**类型*/
    private Integer resourceType;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date resourceCreatetime;
    
    /**更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date resourceUpdatetime;
    
    /**大小*/
    private String resourceSize;
    
    /**状态（暂存0，审核1，审核未通过2，通过审核3）*/
    private Integer resourceState;
    
    /**作者名字（昵称）*/
    private String authorName;
    
    /**作者头像*/
    private String authorAvatar;
    


}

