package com.kang.service;

import com.kang.entity.Praise;
import com.kang.domain.Result;

import java.util.Map;

/**
 * (Praise)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
public interface PraiseService {

    Map<Object,Object> getPraiseNum(Praise praise);

    int delPraise(Praise praise);

    int savePraise(Praise praise);
}

