package com.kang.service.impl;

import com.kang.entity.Inform;
import com.kang.entity.vo.QueryInformVo;
import com.kang.mapper.InformMapper;
import com.kang.service.InformService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<Inform> queryInformList(QueryInformVo queryInformVo) {
        return informMapper.selectByParam(queryInformVo);
    }

    @Override
    public int markRead(List<String> ids) {
        return informMapper.updateStateByIds(ids);
    }

    @Override
    public int delInforms(List<String> ids) {
        return informMapper.deleteByIds(ids);
    }
}

