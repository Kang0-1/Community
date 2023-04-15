package com.kang.mapper;

import com.kang.entity.Resource;
import com.kang.entity.vo.QueryResourceVo;
import com.kang.entity.vo.WorkStateNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Resource)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Mapper
public interface ResourceMapper {

    List<Resource> selectByParam(QueryResourceVo queryResourceVo);

    int updateByPrimaryKeySelective(Resource resource);

    int insertSelective(Resource resource);

    WorkStateNum selectNum(Long uid);

    List<Resource> selectByIds(List<String> idList);

    int deleteByPrimaryKey(String rid);

    Resource selectByPrimaryKey(String rid);
}


