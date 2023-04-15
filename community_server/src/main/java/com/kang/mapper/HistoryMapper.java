package com.kang.mapper;

import com.kang.entity.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (History)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Mapper
public interface HistoryMapper {

    int deleteByPrimaryKeys(List<String> idList);

    List<History> selectByParam(History history);

    int updateByPrimaryKey(History history);

    int insertSelective(History history);
}


