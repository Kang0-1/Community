package com.kang.service.impl;

import cn.hutool.core.map.MapUtil;
import com.kang.entity.Praise;
import com.kang.mapper.PraiseMapper;
import com.kang.service.PraiseService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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

        @Override
        public Map<Object,Object> getPraiseNum(Praise praise) {
                int num=praiseMapper.selectByParam(praise);
                boolean isPraise = num > 0 ? true:false;
                praise.setPraiseId(null);
                num=praiseMapper.selectByParam(praise);
                return MapUtil.builder().put("isPraise",isPraise).put("praiseNum",num).build();
        }

        @Override
        public int delPraise(Praise praise) {
                return praiseMapper.deleteByParam(praise);
        }

        @Override
        public int savePraise(Praise praise) {
                return praiseMapper.insert(praise);
        }
}

