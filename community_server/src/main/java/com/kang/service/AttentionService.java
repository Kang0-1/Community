package com.kang.service;

import com.kang.entity.Attention;

import java.util.List;

/**
 * (Attention)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
public interface AttentionService {

    int judgeAttention(Attention attention);

    int saveAttention(Attention attention);

    int delAttention(Attention attention);

    List<Attention> getAttentionList(int type, Long id);
}

