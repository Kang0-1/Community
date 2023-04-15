package com.kang.service;

import com.kang.entity.History;
import com.kang.entity.Video;
import com.kang.domain.Result;
import com.kang.entity.vo.QueryVideoVo;
import com.kang.entity.vo.WorkStateNum;

import java.util.List;
import java.util.Map;

/**
 * (Video)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
public interface VideoService {

    List<Video> getListByParam(QueryVideoVo queryVideoVo);


    Map<Object, Object> saveVideo(Video video);

    /**
     * 点击获取视频详情，并添加历史记录
     *
     * @param history authorId,worksId
     * @return video
     */
    Video getVideoInfo(History history);

    Video getVideo(String vid);

    int delVideo(String vid);

    WorkStateNum getNumList(Long uid);
}

