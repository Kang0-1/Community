package com.kang.service.impl;

import cn.hutool.core.map.MapUtil;
import com.kang.entity.Article;
import com.kang.entity.Collect;
import com.kang.entity.Video;
import com.kang.mapper.ArticleMapper;
import com.kang.mapper.CollectMapper;
import com.kang.mapper.VideoMapper;
import com.kang.service.CollectService;
import com.kang.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (Collect)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Service("collectService")
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectMapper collectMapper;

    @Resource
    ArticleMapper articleMapper;

    @Resource
    VideoMapper videoMapper;

    @Override
    public Map<Object, Object> getCollectNum(Collect collect) {
        // 查询是否已收藏
        int num = collectMapper.selectByParam(collect).size();
        boolean isCollect= num > 0;
        // 查看该作品的收藏数 (只传 worksId 参数)
        collect.setCollectId(null);
        num=collectMapper.selectByParam(collect).size();
        return MapUtil.builder().put("isCollect",isCollect).put("collectNum",num).build();
    }

    @Override
    public int delCollect(Collect collect) {
        return collectMapper.deleteByParam(collect);
    }

    @Override
    public int saveCollect(Collect collect) {
        collect.setCollectCreatetime(new Date());
        return collectMapper.insertSelective(collect);
    }

    @Override
    public Map<Object, Object> getCollectWorks(String fid) {
        List<String> ids=collectMapper.selectWorksByParam(fid);
        // collect收藏中既有video又有article
        if(ids.size()<=0){
            return MapUtil.builder().put("videoList",null).put("articleList",null).build();
        }
        List<Article> articles = articleMapper.selectByIds(ids);
        List<Video> videos=videoMapper.selectByIds(ids);
        return MapUtil.builder().put("videoList",videos).put("articleList",articles).build();
    }
}

