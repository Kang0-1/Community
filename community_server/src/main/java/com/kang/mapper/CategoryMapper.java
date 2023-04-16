package com.kang.mapper;

import com.kang.domain.Result;
import com.kang.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Mapper
public interface CategoryMapper {

    List<Category> selectByCode(int code);

    List<Category> getCategoryList();

    int updateByPrimaryKeySelective(Category category);

    int insert(Category category);

    int deleteByPrimaryKey(String cid);
}


