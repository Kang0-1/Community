package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (History)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**id*/
    private String historyId;
    
    /**作者id*/
    private Long authorId;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date historyCreatetime;

    /**
     * 作品类型(0:视频，1：文章，2：问答)
     */
    private Integer worksType;
    
    /**作品id*/
    private String worksId;

    /**
     * 其他参数
     */
    private String firstImg;

    private String worksTitle;

    private String worksAuthor;

    private String worksAvatar;

    private String worksCategory;

    private Integer worksCoin;


}

