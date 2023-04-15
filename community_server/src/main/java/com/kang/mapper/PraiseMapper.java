package com.kang.mapper;

import com.kang.entity.Praise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Praise)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Mapper
public interface PraiseMapper {

    int selectByParam(Praise praise);

    int deleteByParam(Praise praise);

    int insert(Praise praise);
}


