package com.kang.service.impl;

import com.kang.entity.Category;
import com.kang.mapper.CategoryMapper;
import com.kang.service.CategoryService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

        @Override
        public List<Category> queryCategoryByCode(int code) {
                return categoryMapper.selectByCode(code);
        }
}

