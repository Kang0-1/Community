package com.kang.service.impl;

import com.kang.entity.Inform;
import com.kang.mapper.InformMapper;
import com.kang.service.InformService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Inform)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Service("informService")
public class InformServiceImpl implements InformService {
        @Resource
        private InformMapper informMapper;

}

