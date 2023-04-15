package com.kang.service;

import com.kang.entity.Collect;
import com.kang.domain.Result;

import java.util.Map;

/**
 * (Collect)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
public interface CollectService {

    Map<Object, Object> getCollectNum(Collect collect);

    int delCollect(Collect collect);

    int saveCollect(Collect collect);

    Map<Object, Object> getCollectWorks(String fid);
}

