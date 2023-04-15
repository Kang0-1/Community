package com.kang.mapper;

import com.kang.entity.Article;
import com.kang.entity.vo.QueryArticleVo;
import com.kang.entity.vo.WorkStateNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@Mapper
public interface ArticleMapper {

    int deleteByPrimaryKey(String articleId);

    Article selectByPrimaryKey(String articleId);

    List<Article> selectByParam(QueryArticleVo queryArticleVo);

    int updateByPrimaryKey(Article article);

    int updateByPrimaryKeySelective(Article record);

    int insertSelective(Article article);

    List<Article> selectByIds(List<String> idList);

    WorkStateNum selectNum(Long uid);
}


