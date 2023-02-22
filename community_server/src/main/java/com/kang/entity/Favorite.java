package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Favorite)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**id*/
    private String favoriteId;
    
    /**收藏夹名字*/
    private String favoriteName;
    
    /**创建者*/
    private Long favoriteOwner;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date favoriteCreatetime;
    


}

