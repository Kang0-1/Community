package com.kang.service.impl;

import com.kang.entity.Attention;
import com.kang.mapper.AttentionMapper;
import com.kang.service.AttentionService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Attention)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Service("attentionService")
public class AttentionServiceImpl implements AttentionService {
        @Resource
        private AttentionMapper attentionMapper;

}

