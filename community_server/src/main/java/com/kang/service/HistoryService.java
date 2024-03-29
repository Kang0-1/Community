package com.kang.service;

import com.kang.entity.History;
import com.kang.domain.Result;

import java.util.List;

/**
 * (History)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
public interface HistoryService {

    List<History> getHistoryList(Long uid);

    int delHistory(List<String> idList);
}

