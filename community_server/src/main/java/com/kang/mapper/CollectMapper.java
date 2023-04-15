package com.kang.mapper;

import com.kang.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Collect)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Mapper
public interface CollectMapper {

    List<Collect> selectByParam(Collect collect);

    int deleteByParam(Collect collect);

    int insertSelective(Collect collect);

    List<String> selectWorksByParam(String fid);

    int selectNum(String favoriteId);
}


