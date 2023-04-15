package com.kang.service;

import com.kang.entity.Article;
import com.kang.domain.Result;
import com.kang.entity.History;
import com.kang.entity.vo.QueryArticleVo;
import com.kang.entity.vo.WorkStateNum;

import java.util.List;
import java.util.Map;


/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
public interface ArticleService {

    List<Article> getListByParam(QueryArticleVo queryArticleVo);

    Map<Object, Object> saveArticle(Article article);

    List<Article> getListByIds(String ids);

    Article getArticleInfo(History history);


    WorkStateNum getNumList(Long uid);

    int delArticle(String aid);

    Article getArticle(String aid);
}

