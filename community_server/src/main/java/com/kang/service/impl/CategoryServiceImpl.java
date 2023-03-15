package com.kang.service.impl;

import com.kang.entity.Category;
import com.kang.mapper.CategoryMapper;
import com.kang.service.CategoryService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
        @Resource
        private CategoryMapper categoryMapper;

}

