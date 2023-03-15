package com.kang.service.impl;

import com.kang.entity.Favorite;
import com.kang.mapper.FavoriteMapper;
import com.kang.service.FavoriteService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}

