package com.kang.service;

import com.kang.entity.Inform;
import com.kang.domain.Result;
import com.kang.entity.vo.QueryInformVo;

import java.util.List;
import java.util.Map;

/**
 * (Inform)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
public interface InformService {

    List<Inform> queryInformList(QueryInformVo queryInformVo);

    int markRead(List<String> ids);

    int delInforms(List<String> ids);
}

