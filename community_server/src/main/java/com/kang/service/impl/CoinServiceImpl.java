package com.kang.service.impl;

import com.kang.entity.Coin;
import com.kang.mapper.CoinMapper;
import com.kang.service.CoinService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Coin)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service("coinService")
public class CoinServiceImpl implements CoinService {
        @Resource
        private CoinMapper coinMapper;

}

