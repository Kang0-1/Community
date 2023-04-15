package com.kang.mapper;

import com.kang.entity.Inform;
import com.kang.entity.vo.QueryInformVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Inform)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Mapper
public interface InformMapper {

    List<Inform> selectByParam(QueryInformVo queryInformVo);

    int updateStateByIds(List<String> ids);

    int deleteByIds(List<String> ids);
}


