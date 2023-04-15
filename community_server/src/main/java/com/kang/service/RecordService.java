package com.kang.service;

import com.kang.entity.Record;
import com.kang.domain.Result;

import java.util.List;

/**
 * (Record)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
public interface RecordService {

    List<Record> getRecordList(Record record);

}

