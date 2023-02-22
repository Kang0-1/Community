package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Category)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**分类id*/
    private String categoryId;
    
    /**分类编码*/
    private Integer categoryCode;
    
    /**分类名称*/
    private String categoryName;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date categoryCreatetime;
    
    /**作品类型（哪个作品的分类：0视屏分类，1文章分类，2提问分类，3资源分类）*/
    private Integer workesType;
    


}

