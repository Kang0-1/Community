package com.kang.service.impl;

import com.kang.entity.History;
import com.kang.mapper.HistoryMapper;
import com.kang.service.HistoryService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (History)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service("historyService")
public class HistoryServiceImpl implements HistoryService {
        @Resource
        private HistoryMapper historyMapper;

}

