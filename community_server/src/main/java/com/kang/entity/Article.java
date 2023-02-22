package com.kang.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;



/**
 * (Article)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**文章id*/
    private String articleId;
    
    /**文章标题*/
    private String articleTitle;
    
    /**封面*/
    private String firstImg;
    
    /**分类*/
    private Integer articleCategory;
    
    /**类别*/
    private Integer articleType;
    
    /**简介*/
    private String articleSummary;
    
    /**内容*/
    private String articleContent;
    
    /**观看数*/
    private Integer articleView;
    
    /**状态（暂存0，审核1，审核未通过2，通过审核3）*/
    private Integer articleState;
    
    /**所需T币 */
    private Integer articleCoin;
    
    /**所属专辑*/
    private String articleAlbum;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date articleCreatetime;
    
    /**更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date articleUpdatetime;
    
    /**头像*/
    private String authorAvatar;
    
    /**文章作者*/
    private Long authorId;
    
    /**使用的编辑器（富文本，markdown）*/
    private Integer articleEditor;
    
    /**评论区开关（如果是付费的，则评论区不可关）*/
    private Integer commentSwitch;
    
    /**作者名字*/
    private String authorName;
    


}

