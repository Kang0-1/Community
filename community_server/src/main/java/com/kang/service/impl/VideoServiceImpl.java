package com.kang.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.kang.entity.Album;
import com.kang.entity.History;
import com.kang.entity.Video;
import com.kang.entity.vo.QueryVideoVo;
import com.kang.entity.vo.VideoListVo;
import com.kang.entity.vo.WorkStateNum;
import com.kang.mapper.AlbumMapper;
import com.kang.mapper.HistoryMapper;
import com.kang.mapper.VideoMapper;
import com.kang.service.VideoService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * (Video)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service("videoService")
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoMapper videoMapper;

    @Resource
    private AlbumMapper albumMapper;

    @Resource
    private HistoryMapper historyMapper;

    @Override
    public List<Video> getListByParam(QueryVideoVo queryVideoVo) {
        return videoMapper.selectByParam(queryVideoVo);
    }

    @Override
    public Map<Object, Object> saveVideo(Video video) {
        // 设置基本信息
        video.setVideoView(0);
        video.setVideoCreatetime(new Date());
        video.setAssociatedArticle(video.getAssociatedArticle());
        video.setAssociatedResource(video.getAssociatedResource());
        video.setVideoState(video.getVideoState());
        List<VideoListVo> videoList = video.getVideoList();

        // 如果视频列表大于1且albumId为空，则把视频放入合集中
        if (videoList.size() > 1) {
            String albumId = video.getVideoAlbum();
            if (StrUtil.isEmpty(albumId)) {
                Album album = new Album();
                albumId = UUID.randomUUID().toString();
                album.setAlbumId(albumId);
                album.setAlbumTitle(video.getVideoTitle());
                album.setFirstImage(video.getFirstImg());
                album.setAlbumType(0); // 0:视频  1:文章
                album.setAuthorId(video.getAuthorId());
                album.setAlbumCreatetime(new Date());
                album.setAlbumUpdatetime(new Date());
                albumMapper.insertSelective(album);
            }
            video.setVideoAlbum(albumId);
            for (int i = 0; i < videoList.size(); i++) {
                String url = videoList.get(i).getUrl();
                video.setVideoUrl(url);
                // 1.多个视频，title是文件名，详情页显示的是合集的title
                // 2.单个视频，title就是用户输入的title
                String fileName = videoList.get(i).getName();
                video.setVideoTitle(fileName.substring(0, fileName.lastIndexOf('.'))); // 名字去掉 .mp4后缀
                // 如果视频列表的id为空则插入新的，否则更新原来的
                if (StrUtil.isEmpty(videoList.get(i).getId())) {
                    String videoId = UUID.randomUUID().toString();
                    video.setVideoId(videoId);
                    videoList.get(i).setId(videoId);
                    videoMapper.insertSelective(video);
                } else {
                    video.setVideoId(videoList.get(i).getId());
                    videoMapper.updateByPrimaryKeySelective(video);
                }
            }
            return MapUtil.builder()
                    .put("success", "保存成功!")
                    .put("albumId", albumId)
                    .put("videoList", videoList)
                    .build();
        } else if (videoList.size() == 1) {
            video.setVideoUrl(videoList.get(0).getUrl());
            if (StrUtil.isEmpty(videoList.get(0).getId())) {
                String videoId = UUID.randomUUID().toString();
                videoList.get(0).setId(videoId);
                video.setVideoId(videoId);
                videoMapper.insertSelective(video);
            } else {
                video.setVideoId(videoList.get(0).getId());
                videoMapper.updateByPrimaryKeySelective(video);
            }
            return MapUtil.builder()
                    .put("success", "保存成功!")
                    .put("albumId", "")
                    .put("videoList", videoList)
                    .build();
        } else {
            return MapUtil.builder().put("error", "保存失败:视频地址为空！").build();
        }
    }

    @Override
    public Video getVideoInfo(History history) {
        Video video = videoMapper.selectByPrimaryKey(history.getWorksId());
        // 访问增加访问次数
        video.setVideoView(video.getVideoView()+1);

        // 先查询历史记录里有没有这个作品，有则更新时间，没有则添加新的记录
        List<History> histories = historyMapper.selectByParam(history);
        if(histories.size()>0){
            history=histories.get(0);
            history.setHistoryCreatetime(new Date());
            historyMapper.updateByPrimaryKey(history);
        }else {
            history.setHistoryId(UUID.randomUUID().toString());
            history.setHistoryCreatetime(new Date());
            history.setWorksType(0);
            historyMapper.insertSelective(history);
        }

        // 更新到数据中
        videoMapper.updateByPrimaryKeySelective(video);
        return video;
    }

    @Override
    public Video getVideo(String vid) {
        return videoMapper.selectByPrimaryKey(vid);
    }

    @Override
    public int delVideo(String vid) {
        return videoMapper.deleteByPrimaryKey(vid);
    }

    @Override
    public WorkStateNum getNumList(Long uid) {
        return videoMapper.selectNum(uid);
    }


}

