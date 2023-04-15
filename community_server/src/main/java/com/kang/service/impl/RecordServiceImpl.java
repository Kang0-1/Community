package com.kang.service.impl;

import com.kang.entity.Record;
import com.kang.mapper.RecordMapper;
import com.kang.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Record)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service("recordService")
public class RecordServiceImpl implements RecordService {
        @Resource
        private RecordMapper recordMapper;

        @Override
        public List<Record> getRecordList(Record record) {
                return recordMapper.selectByParam(record);
        }
}

