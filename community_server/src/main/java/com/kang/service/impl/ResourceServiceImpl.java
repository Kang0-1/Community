package com.kang.service.impl;

import com.kang.entity.Resource;
import com.kang.mapper.ResourceMapper;
import com.kang.service.ResourceService;
import com.kang.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




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

}

