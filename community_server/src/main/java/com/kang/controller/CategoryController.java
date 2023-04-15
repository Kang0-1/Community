package com.kang.controller;


import com.kang.domain.Result;
import com.kang.entity.Category;
import com.kang.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Category)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@RestController
@RequestMapping()
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * @param code code 作品类型（哪个作品的分类：0视屏分类，1文章分类，2提问分类，3资源分类）
     * @return Category
     */
    @GetMapping("/category/list/{code}")
    public Result queryCategory(@PathVariable int code){
        return Result.success(categoryService.queryCategoryByCode(code));
    }
}


