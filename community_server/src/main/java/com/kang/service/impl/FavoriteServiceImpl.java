package com.kang.service.impl;

import com.kang.entity.Favorite;
import com.kang.mapper.CollectMapper;
import com.kang.mapper.FavoriteMapper;
import com.kang.service.FavoriteService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (Favorite)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteMapper favoriteMapper;

    @Resource
    private CollectMapper collectMapper;



    @Override
    public List<Favorite> getList(Long uid) {
        Favorite favor=new Favorite();
        favor.setFavoriteOwner(uid);
        // 通过 uid 查出收藏夹列表
        List<Favorite> favoriteList=favoriteMapper.selectByParam(favor);
        // 查出每个收藏夹中的收藏作品数量
        for(Favorite favorite:favoriteList){
            favorite.setWorksNum(collectMapper.selectNum(favorite.getFavoriteId()));
        }
        return favoriteList;
    }

    @Override
    public int saveFavorite(Favorite favorite) {
        List<Favorite> favoriteList = favoriteMapper.selectByParam(favorite);
        // 查询出该收藏夹已存在，创建失败并返回
        if(favoriteList.size()>0){
            return -1;
        }
        favorite.setFavoriteId(UUID.randomUUID().toString());
        favorite.setFavoriteCreatetime(new Date());
        return favoriteMapper.insertSelective(favorite);
    }
}

