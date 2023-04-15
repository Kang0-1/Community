package com.kang.service;

import com.kang.entity.Category;
import com.kang.domain.Result;

import java.util.List;

/**
 * (Category)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
public interface CategoryService {

    /**
     * 根据 workesType 字段值 分类
     * @param code 作品类型（哪个作品的分类：0视屏分类，1文章分类，2提问分类，3资源分类）
     * @return Category
     */
    List<Category> queryCategoryByCode(int code);
}

