package com.kang.mapper;

import com.kang.entity.Attention;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Attention)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Mapper
public interface AttentionMapper {

    int selectByJudge (Attention attention);

    List<Attention> selectByParam(Attention attention);

    int insert(Attention attention);

    int deleteByParam(Attention attention);
}


