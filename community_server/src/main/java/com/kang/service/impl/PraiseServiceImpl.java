package com.kang.service.impl;

import com.kang.entity.Praise;
import com.kang.mapper.PraiseMapper;
import com.kang.service.PraiseService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Praise)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Service("praiseService")
public class PraiseServiceImpl implements PraiseService {
        @Resource
        private PraiseMapper praiseMapper;

}

