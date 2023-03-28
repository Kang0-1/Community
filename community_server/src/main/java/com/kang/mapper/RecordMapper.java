package com.kang.mapper;

import com.kang.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Record)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Mapper
public interface RecordMapper {

    void insertSelective(Record record);
}


