package com.kang.service;

import com.kang.entity.Album;
import com.kang.domain.Result;

import java.util.List;

/**
 * 1(Album)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
public interface AlbumService {

    List<Album> queryAlbumByParam(Album album);
}

