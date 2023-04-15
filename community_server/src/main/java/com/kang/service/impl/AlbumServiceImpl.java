package com.kang.service.impl;

import com.kang.entity.Album;
import com.kang.domain.Result;
import com.kang.mapper.AlbumMapper;
import com.kang.service.AlbumService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 1(Album)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service
public class AlbumServiceImpl implements AlbumService {
        @Resource
        private AlbumMapper albumMapper;

        @Override
        public List<Album> queryAlbumByParam(Album album) {
                return albumMapper.selectByParam(album);
        }
}

