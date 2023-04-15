package com.kang.service.impl;

import com.kang.entity.Attention;
import com.kang.mapper.AttentionMapper;
import com.kang.service.AttentionService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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

        @Override
        public int judgeAttention(Attention attention) {
                return attentionMapper.selectByJudge(attention);
        }

        @Override
        public int saveAttention(Attention attention) {
                attention.setAttentionId(UUID.randomUUID().toString());
                return attentionMapper.insert(attention);
        }

        @Override
        public int delAttention(Attention attention) {
                return attentionMapper.deleteByParam(attention);
        }

        @Override
        public List<Attention> getAttentionList(int type, Long id) {
                Attention attention=new Attention();
                if(type==0){
                        // 查关注类表
                        attention.setFansId(id);
                }
                if(type==1){
                        // 查粉丝类表
                        attention.setIdolId(id);
                }
                List<Attention> attentionList=attentionMapper.selectByParam(attention);
                return attentionList;
        }
}

