package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * 1(Album)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**合集id*/
    private String albumId;
    
    /**作者*/
    private Long authorId;
    
    /**封面*/
    private String firstImage;
    
    /**标题*/
    private String albumTitle;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date albumCreatetime;
    
    /**更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date albumUpdatetime;
    
    /**合集里放的作品种类*/
    private Integer albumType;
    


}

