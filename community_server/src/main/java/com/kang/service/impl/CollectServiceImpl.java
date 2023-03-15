package com.kang.service.impl;

import com.kang.entity.Collect;
import com.kang.mapper.CollectMapper;
import com.kang.service.CollectService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Collect)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Service("collectService")
public class CollectServiceImpl implements CollectService {
        @Resource
        private CollectMapper collectMapper;

}

