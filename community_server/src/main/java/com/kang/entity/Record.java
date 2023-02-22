package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Record)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**id*/
    private String recordId;
    
    /**描述*/
    private String recordDescribe;
    
    /**作者id*/
    private Long authorId;
    
    /**硬币变化*/
    private String coinChange;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date recordCreatetime;
    
    /**作品类型*/
    private Integer worksType;
    
    /**作品id*/
    private String worksId;
    


}

