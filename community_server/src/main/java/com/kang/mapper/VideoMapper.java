package com.kang.mapper;

import com.kang.entity.User;
import com.kang.entity.Video;
import com.kang.entity.vo.QueryVideoVo;
import com.kang.entity.vo.WorkStateNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Video)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Mapper
public interface VideoMapper {

    Video selectByPrimaryKey(String videoId);

    List<Video> selectByParam(QueryVideoVo queryVideoVo);

    int insertSelective(Video video);

    int updateByPrimaryKeySelective(Video video);

    WorkStateNum selectNum(Long uid);

    int deleteByPrimaryKey(String vid);

    List<Video> selectByIds(List<String> ids);

    int updateUserInfoByUserId(User user);
}


