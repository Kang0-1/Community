package com.kang.service.impl;

import com.kang.entity.Article;
import com.kang.mapper.ArticleMapper;
import com.kang.service.ArticleService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Service
public class ArticleServiceImpl implements ArticleService {
        @Resource
        private ArticleMapper articleMapper;

}

