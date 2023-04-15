package com.kang.entity;

import java.util.Date;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Question)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**id*/
    private String qaId;
    
    /**标题*/
    private String qaTitle;
    
    /**内容*/
    private String qaContent;
    
    /**发布者id*/
    private Long authorId;
    
    /**概述*/
    private String qaSummary;
    
    /**赏金*/
    private Integer qaReward;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date qaCreatetime;
    
    /**更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date qaUpdatetime;
    
    /**问题分类*/
    private Integer qaCategory;
    
    /**采纳的答案的id*/
    private String acceptId;
    
    /**发布者名字（昵称）*/
    private String authorName;
    
    /**提问状态（暂存0，审核1，审核未通过2，通过审核3）*/
    private Integer qaState;
    
    /**头像*/
    private String authorAvatar;

    /**
     * 其他数据
     */
    private String qaCategoryName;



    public void setQaCategoryName(String qaCategoryName) {
        if (StrUtil.isEmpty(qaCategoryName)) {
            this.qaCategoryName = "其他";
        } else {
            this.qaCategoryName = qaCategoryName;
        }
    }
    


}

