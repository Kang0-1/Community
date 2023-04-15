package com.kang.mapper;

import com.kang.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Favorite)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Mapper
public interface FavoriteMapper {

    int insertSelective(Favorite favorite);

    List<Favorite> selectByParam(Favorite favor);
}


