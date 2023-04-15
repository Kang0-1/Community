package com.kang.service.impl;

import cn.hutool.core.util.StrUtil;
import com.kang.entity.Article;
import com.kang.entity.History;
import com.kang.entity.Question;
import com.kang.entity.Video;
import com.kang.mapper.ArticleMapper;
import com.kang.mapper.HistoryMapper;
import com.kang.mapper.QuestionMapper;
import com.kang.mapper.VideoMapper;
import com.kang.service.HistoryService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (History)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service("historyService")
public class HistoryServiceImpl implements HistoryService {
    @Resource
    private HistoryMapper historyMapper;

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public List<History> getHistoryList(Long uid) {
        History history = new History();
        history.setAuthorId(uid);
        List<History> histories = historyMapper.selectByParam(history);
        // 查出来的历史记录列表要通过作品类型(0 : 视频，1 : 文章 , 2 : 提问) 和id查询对应的详细信息
        for (History his : histories) {
            if (his.getWorksType() == 0) {
                // 视频
                Video video = videoMapper.selectByPrimaryKey(his.getWorksId());
                his.setFirstImg(video.getFirstImg());
                his.setWorksTitle(video.getVideoTitle());
                his.setWorksAuthor(video.getAuthorName());
                his.setWorksAvatar(video.getAuthorAvatar());
                his.setWorksCategory(video.getVideoCategoryName());
                his.setWorksCoin(video.getVideoCoin());
            }
            if(his.getWorksType()==1){
                // 文章
                Article article=articleMapper.selectByPrimaryKey(his.getWorksId());
                his.setFirstImg(article.getFirstImg());
                his.setWorksTitle(article.getArticleTitle());
                his.setWorksAuthor(article.getAuthorName());
                his.setWorksAvatar(article.getAuthorAvatar());
                his.setWorksCategory(article.getArticleCategoryName());
                his.setWorksCoin(article.getArticleCoin());
            }
            if(his.getWorksType()==2){
                // 提问
                Question question=questionMapper.selectByPrimaryKey(his.getWorksId());
                // 提问没有封面图，图片放悬赏金额
                his.setFirstImg(String.valueOf(question.getQaReward()));
                his.setWorksTitle(question.getQaTitle());
                his.setWorksAuthor(question.getAuthorName());
                his.setWorksAvatar(question.getAuthorAvatar());
                his.setWorksCategory(question.getQaCategoryName());
                // 没有 coin，提问的状态，是否已解决
                his.setWorksCoin( StrUtil.isEmpty(question.getAcceptId()) ? 0 : 1 );
            }
        }
        return histories;
    }

    @Override
    public int delHistory(List<String> idList) {
        return historyMapper.deleteByPrimaryKeys(idList);
    }
}

