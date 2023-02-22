package com.kang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Attention)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attention implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**id*/
    private String attentionId;
    
    /**偶像（被关注者）*/
    private Long idolId;
    
    /**粉丝（关注者）*/
    private Long fansId;
    


}

