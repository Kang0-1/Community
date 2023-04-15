package com.kang.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.kang.entity.Album;
import com.kang.entity.Article;
import com.kang.entity.History;
import com.kang.entity.vo.QueryArticleVo;
import com.kang.entity.vo.WorkStateNum;
import com.kang.mapper.AlbumMapper;
import com.kang.mapper.ArticleMapper;
import com.kang.mapper.HistoryMapper;
import com.kang.service.ArticleService;
import com.kang.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private AlbumMapper albumMapper;

    @Resource
    private HistoryMapper historyMapper;

    @Override
    public List<Article> getListByParam(QueryArticleVo queryArticleVo) {
        return articleMapper.selectByParam(queryArticleVo);
    }

    @Override
    public Map<Object, Object> saveArticle(Article article) {
        // 如果AlbumName不为空，Album为空 表示创建文章时新建一个合集，放入这个合集
        if(StrUtil.isNotEmpty(article.getAlbumName()) && StrUtil.isEmpty(article.getArticleAlbum())){
            Album album=new Album();
            String albumId= UUID.randomUUID().toString();
            album.setAlbumId(albumId);
            album.setAlbumTitle(article.getAlbumName());
            album.setFirstImage(article.getFirstImg());
            album.setAlbumType(1); // 0:视频  1:文章
            album.setAuthorId(article.getAuthorId());
            album.setAlbumCreatetime(new Date());
            album.setAlbumUpdatetime(new Date());
            int i=albumMapper.insertSelective(album);
            if(i==1){
                article.setArticleAlbum(albumId);
            }
        }
        int i=0;
        // 判断 article 是修改（update）还是新增(insert)
        if(StrUtil.isNotEmpty(article.getArticleId())){
            i=articleMapper.updateByPrimaryKey(article);
        }else {
            String articleId=UUID.randomUUID().toString();
            article.setArticleId(articleId);
            article.setArticleView(1);
            article.setArticleCreatetime(new Date());
            i=articleMapper.insertSelective(article);
        }
        if(i>0){
            return MapUtil.builder().put("articleId",article.getArticleId()).build();
        }
        return MapUtil.builder().put("error","保存失败").build();
    }

    @Override
    public List<Article> getListByIds(String ids) {
        String[] split = ids.split(",");
        List<String> idList= Arrays.asList(split);
        return articleMapper.selectByIds(idList);
    }

    @Override
    public Article getArticleInfo(History history) {
        Article article = articleMapper.selectByPrimaryKey(history.getWorksId());
        // 增加观看数
        article.setArticleView(article.getArticleView()+1);
        // 先查询历史记录表有没有这个作品，如果有，更新时间，否则添加新的记录
        List<History> histories = historyMapper.selectByParam(history);
        if(histories.size()>0){
            history=histories.get(0);
            history.setHistoryCreatetime(new Date());
            historyMapper.updateByPrimaryKey(history);
        }else {
            history.setHistoryId(UUID.randomUUID().toString());
            history.setHistoryCreatetime(new Date());
            history.setWorksType(1);
            historyMapper.insertSelective(history);
        }
        articleMapper.updateByPrimaryKeySelective(article);
        return article;
    }

    @Override
    public WorkStateNum getNumList(Long uid) {
        return articleMapper.selectNum(uid);
    }

    @Override
    public int delArticle(String aid) {
        return articleMapper.deleteByPrimaryKey(aid);

    }

    @Override
    public Article getArticle(String aid) {
        return articleMapper.selectByPrimaryKey(aid);
    }
}

