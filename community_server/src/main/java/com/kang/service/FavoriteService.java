package com.kang.service;

import com.kang.entity.Favorite;
import com.kang.domain.Result;

import java.util.List;

/**
 * (Favorite)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
public interface FavoriteService {

    List<Favorite> getList(Long uid);

    int saveFavorite(Favorite favorite);
}

