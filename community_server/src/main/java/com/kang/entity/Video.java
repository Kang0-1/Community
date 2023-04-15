package com.kang.entity;

import java.util.Date;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kang.entity.vo.VideoListVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;


/**
 * (Video)表实体类
 *
 * @author makejava
 * @since 2023-02-22 22:43:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**视频id*/
    private String videoId;
    
    /**标题*/
    private String videoTitle;
    
    /**封面*/
    private String firstImg;
    
    /**分类*/
    private Integer videoCategory;
    
    /**类别（自制，转载）*/
    private Integer videoType;
    
    /**视频地址*/
    private String videoUrl;
    
    /**简介*/
    private String videoSummary;
    
    /**观看数*/
    private long videoView;
    
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date videoCreatetime;
    
    /**更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date videoUpdatetime;
    
    /**视屏状态度（暂存，审核，锁定，通过审核，被举报）*/
    private Integer videoState;
    
    /**观看视频需要的硬币*/
    private Integer videoCoin;
    
    /**视频所属合集id*/
    private String videoAlbum;
    
    /**视屏所关联的文章id*/
    private String associatedArticle;
    
    /**视屏所关联的资源id*/
    private String associatedResource;
    
    /**作者id*/
    private long authorId;
    
    /**评论区开关（如果是付费的，则评论区不可关）*/
    private Integer commentSwitch;
    
    /**作者名字*/
    private String authorName;
    
    /**作者头像*/
    private String authorAvatar;

    /**
       * 其他属性
     */
    private List<VideoListVo> videoList;

    private String videoCategoryName;

    public void setVideoCategoryName(String videoCategoryName) {
        if (StrUtil.isEmpty(videoCategoryName)) {
            this.videoCategoryName = "其他";
        } else {
            this.videoCategoryName = videoCategoryName;
        }
    }

}

