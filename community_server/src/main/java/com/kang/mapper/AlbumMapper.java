package com.kang.mapper;

import com.kang.domain.Result;
import com.kang.entity.Album;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Album)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Mapper
public interface AlbumMapper {

    List<Album> selectByParam(Album album);

    int insertSelective(Album album);
}


