package com.kang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Praise)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Praise implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**点赞者的id*/
    private Long praiseId;
    
    /**作品id*/
    private String worksId;
    
    /**作品作者的id（方便查找总点赞数，计算收益）*/
    private Long authorId;
    


}

