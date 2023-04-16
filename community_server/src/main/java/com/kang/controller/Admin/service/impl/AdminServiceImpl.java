package com.kang.controller.Admin.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.kang.controller.Admin.service.AdminService;
import com.kang.entity.*;
import com.kang.entity.vo.QueryArticleVo;
import com.kang.entity.vo.QueryQuestionVo;
import com.kang.entity.vo.QueryResourceVo;
import com.kang.entity.vo.QueryVideoVo;
import com.kang.mapper.*;
import com.kang.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author kang
 * @description
 * @date 2023/4/15 15:32
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    VideoMapper videoMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    InformMapper informMapper;
    @Autowired
    AttentionMapper attentionMapper;

    @Override
    public Map<Object, Object> goLogin(User user) {
        User admin = userMapper.selectUserByParam(user);
        if (!"ROLE_admin".equals(admin.getUserRole()) && !"ROLE_super".equals(admin.getUserRole())) {
            return MapUtil.builder().put("error", "账号权限不足").build();
        }
        if (StrUtil.isEmpty(user.getUserPassword()) || !SecureUtil.md5(user.getUserPassword()).equals(admin.getUserPassword())) {
            return MapUtil.builder().put("error", "密码错误").build();
        }
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("uid", admin.getUserId().toString());
        userInfo.put("nickname", admin.getNickName());
        userInfo.put("username", admin.getUserName());
        userInfo.put("role", admin.getUserRole());
        userInfo.put("Avatar", admin.getUserAvatar());
        String token = jwtUtil.generateToken(admin.getUserId().toString());
        return MapUtil.builder().put("userInfo", userInfo).put("token", token).build();
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public int addAdmin(User user) {
        user.setUserRole("ROLE_admin");
        user.setCreateTime(new Date());
        user.setUserPassword(SecureUtil.md5(user.getUserPassword()));
        return userMapper.insertSelective(user);
    }

    @Override
    public int addAuthority(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int delUser(Long uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public int saveCategory(Category category) {
        if(StrUtil.isNotEmpty(category.getCategoryId())){
            return categoryMapper.updateByPrimaryKeySelective(category);
        }else {
            category.setCategoryId(UUID.randomUUID().toString());
            category.setCategoryCreatetime(new Date());
            return categoryMapper.insert(category);
        }
    }

    @Override
    public int delCategory(String cid) {
        return categoryMapper.deleteByPrimaryKey(cid);
    }

    @Override
    public List<Comment> getCommentList() {
        return commentMapper.selectByParam(new Comment());
    }

    @Override
    public List<Answer> getAnswerList() {
        return answerMapper.selectByParam(new Answer());
    }

    @Override
    public int changeAnswer(String aid) {
        Answer answer=new Answer();
        answer.setAnswerContent("[该回答内容包含违规信息，已被隐藏]");
        answer.setAnswerId(aid);
        return answerMapper.updateByPrimaryKeySelective(answer);
    }

    @Override
    public int changeComment(String cid) {
        Comment comment=new Comment();
        comment.setCommentContent("[该回答内容包含违规信息，已被隐藏]");
        comment.setCommentId(cid);
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public List<Video> getVideoList(Integer state) {
        QueryVideoVo queryVideoVo=new QueryVideoVo();
        queryVideoVo.setVideoState(state);
        return videoMapper.selectByParam(queryVideoVo);
    }

    @Override
    public List<Article> getArticleList(Integer state) {
        QueryArticleVo queryArticleVo=new QueryArticleVo();
        queryArticleVo.setArticleState(state);
        return articleMapper.selectByParam(queryArticleVo);
    }

    @Override
    public List<Question> getQuestionList(Integer state) {
        QueryQuestionVo queryQuestionVo=new QueryQuestionVo();
        queryQuestionVo.setQaState(state);
        return questionMapper.selectByParam(queryQuestionVo);
    }

    @Override
    public List<Resource> getResourceList(Integer state) {
        QueryResourceVo queryResourceVo=new QueryResourceVo();
        queryResourceVo.setResourceState(state);
        return resourceMapper.selectByParam(queryResourceVo);
    }

    @Override
    public int Apply(Inform inform) {
        int workType = inform.getWorkType();
        // pass:   applyState=1->作品state=3(通过审核)
        // reject: applyState=0->作品state=2(审核未通过)
        int state= inform.getApplyState()==1?3:2;
        int i=0;
        // 判断作品类型，然后对其进行审核状态更新
        if(workType==0){
            Video video=new Video();
            video.setVideoId(inform.getWorkId());
            video.setVideoState(state);
            video.setVideoUpdatetime(new Date());
            i=videoMapper.updateByPrimaryKeySelective(video);
        }
        if(workType==1){
            Article article=new Article();
            article.setArticleId(inform.getWorkId());
            article.setArticleState(state);
            article.setArticleUpdatetime(new Date());
            i=articleMapper.updateByPrimaryKeySelective(article);
        }
        if(workType==2){
            Question question=new Question();
            question.setQaId(inform.getWorkId());
            question.setQaUpdatetime(new Date());
            question.setQaState(state);
            i=questionMapper.updateByPrimaryKeySelective(question);
        }
        if(workType==3){
            Resource resource=new Resource();
            resource.setResourceId(inform.getWorkId());
            resource.setResourceUpdatetime(new Date());
            resource.setResourceState(state);
            i=resourceMapper.updateByPrimaryKeySelective(resource);
        }
        if(i>0){
            inform.setInformId(UUID.randomUUID().toString());
            inform.setInformCreatetime(new Date());
            // 0:系统通知
            inform.setInformType(0);
            // 0:未读
            inform.setInformState(0);
            //给作者发生系统通知
            if(state==2){
                // 审核未通过
                inform.setInformContent("您的作品《"+inform.getWorkName()+"》未通过管理员的审核，驳回原因: "+inform.getInformContent());
                informMapper.insertSelective(inform);
            }else {
                // 审核通过，并且给作者的粉丝发送通知
                inform.setInformContent("您的作品《" + inform.getWorkName() + "》已通过管理员的审核，赶快来看看吧！");
                informMapper.insertSelective(inform);

                inform.setInformContent("你关注的用户 @" + inform.getAuthorName()
                        + "  发布了新的作品《" + inform.getWorkName() + "》，赶快来看一看吧");
                // 1:动态通知
                inform.setInformType(1);
                // 遍历粉丝
                Attention attention=new Attention();
                attention.setIdolId(inform.getInformReceiver());
                List<Attention> attentionList = attentionMapper.selectByParam(attention);
                for(Attention user:attentionList){
                    inform.setInformId(UUID.randomUUID().toString());
                    inform.setInformReceiver(user.getFansId());
                    informMapper.insertSelective(inform);
                }
            }
            return 1;
        }else {
            return 0;
        }
    }
}
