package com.kang.service.impl;

import cn.hutool.core.util.StrUtil;
import com.kang.entity.Resource;
import com.kang.entity.vo.QueryResourceVo;
import com.kang.entity.vo.WorkStateNum;
import com.kang.mapper.ResourceMapper;
import com.kang.service.ResourceService;
import com.kang.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * (Resource)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> getListByParam(QueryResourceVo queryResourceVo) {
        return resourceMapper.selectByParam(queryResourceVo);
    }

    @Override
    public int saveResource(Resource resource) {
        if(StrUtil.isNotEmpty(resource.getResourceId())){
            return resourceMapper.updateByPrimaryKeySelective(resource);
        }
        resource.setResourceId(UUID.randomUUID().toString());
        resource.setResourceState(1);
        resource.setResourceCreatetime(new Date());
        resource.setResourceUpdatetime(new Date());
        return resourceMapper.insertSelective(resource);
    }

    @Override
    public List<Resource> getListByIds(String ids) {
        String[] idArr = ids.split(",");
        List<String> idList = Arrays.asList(idArr);
        return resourceMapper.selectByIds(idList);
    }

    @Override
    public WorkStateNum getNumList(Long uid) {
        return resourceMapper.selectNum(uid);
    }

    @Override
    public int delResource(String rid) {
        return resourceMapper.deleteByPrimaryKey(rid);
    }

    @Override
    public Resource getResource(String rid) {
        return resourceMapper.selectByPrimaryKey(rid);
    }


}

