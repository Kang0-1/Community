package com.kang.controller.Admin.service;

import com.kang.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @author kang
 * @description
 * @date 2023/4/15 15:32
 */
public interface AdminService {
    Map<Object, Object> goLogin(User user);

    List<User> getUserList();

    int addAdmin(User user);

    int addAuthority(User user);

    int delUser(Long uid);

    List<Category> getCategoryList();

    int saveCategory(Category category);

    int delCategory(String cid);

    List<Comment> getCommentList();

    List<Answer> getAnswerList();

    int changeAnswer(String aid);

    int changeComment(String cid);

    List<Video> getVideoList(Integer state);

    List<Article> getArticleList(Integer state);

    List<Question> getQuestionList(Integer state);

    List<Resource> getResourceList(Integer state);

    int Apply(Inform inform);
}
