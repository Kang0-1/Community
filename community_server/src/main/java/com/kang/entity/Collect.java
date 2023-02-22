package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Collect)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collect implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**收藏着的id*/
    private Long collectId;
    
    /**作品id*/
    private String worksId;
    
    /**所属收藏夹id*/
    private String collectFavorite;
    
    /**收藏时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date collectCreatetime;
    


}

