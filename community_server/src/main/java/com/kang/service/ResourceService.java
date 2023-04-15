package com.kang.service;

import com.kang.entity.Resource;
import com.kang.domain.Result;
import com.kang.entity.vo.QueryResourceVo;
import com.kang.entity.vo.WorkStateNum;

import java.util.List;

/**
 * (Resource)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
public interface ResourceService {

    List<Resource> getListByParam(QueryResourceVo queryResourceVo);

    int saveResource(Resource resource);

    List<Resource> getListByIds(String ids);

    WorkStateNum getNumList(Long uid);

    int delResource(String rid);

    Resource getResource(String rid);
}

