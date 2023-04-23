package com.kang.controller.Admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kang.controller.Admin.service.AdminService;
import com.kang.domain.Result;
import com.kang.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @author kang
 * @description 后台管理
 * @date 2023/4/15 15:15
 */
@RestController
@RequestMapping()
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 超级管理员
     */

    @PostMapping("/super/admin/add")
    public Result addAdmin(@RequestBody User user){
        return Result.success(adminService.addAdmin(user));
    }

    @PostMapping("/super/authority/add")
    public Result addAuthority(@RequestBody User user){
        return Result.success(adminService.addAuthority(user));
    }

    /**
     * 普通管理员
     */

    @PostMapping("/admin/login")
    public Result login(@RequestBody User user){
        Map<Object,Object> result=adminService.goLogin(user);
        if(result.containsKey("error")){
            return Result.error(result.get("error").toString());
        }else {
            return Result.success(result);
        }
    }

    @GetMapping("/admin/user/list")
    public Result userList(@RequestParam int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList=adminService.getUserList();
        PageInfo pageInfo=new PageInfo(userList);
        return Result.success(pageInfo);
    }

    @GetMapping("/admin/user/del/{uid}")
    public Result delUser(@PathVariable Long uid){
        int i=adminService.delUser(uid);
        if(i>0){
            return Result.success();
        }else {
            return Result.error();
        }

    }

    @GetMapping("/admin/category/list")
    public Result categoryList(@RequestParam int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Category> categories=adminService.getCategoryList();
        PageInfo pageInfo=new PageInfo(categories);
        return Result.success(pageInfo);
    }

    @PostMapping("/admin/category/add")
    public Result addCategory(@RequestBody Category category){
        return Result.success(adminService.saveCategory(category));
    }

    @GetMapping("/admin/category/del/{cid}")
    public Result delCategory(@PathVariable String cid){
        int i=adminService.delCategory(cid);
        if(i>0){
            return Result.success();
        }else {
            return Result.error();
        }

    }

    @GetMapping("/admin/comment/list")
    public Result commentList(@RequestParam int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> commentList=adminService.getCommentList();
        PageInfo pageInfo=new PageInfo(commentList);
        return Result.success(pageInfo);
    }

    @GetMapping("/admin/answer/hidden/{aid}")
    public Result changeAnswer(@PathVariable String aid){
        return Result.success(adminService.changeAnswer(aid));
    }

    @GetMapping("/admin/answer/list")
    public Result list(@RequestParam int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Answer> answerList=adminService.getAnswerList();
        PageInfo pageInfo=new PageInfo(answerList);
        return Result.success(pageInfo);
    }

    @GetMapping("/admin/comment/hidden/{cid}")
    public Result changeComment(@PathVariable String cid){
        return Result.success(adminService.changeComment(cid));
    }

    @GetMapping("/admin/video/list/{state}")
    public Result videoList(@RequestParam int pageNum,int pageSize,@PathVariable Integer state){
        PageHelper.startPage(pageNum,pageSize);
        List<Video> videoList=adminService.getVideoList(state);
        PageInfo pageInfo=new PageInfo(videoList);
        return Result.success(pageInfo);
    }

    @GetMapping("/admin/article/list/{state}")
    public Result articleList(@RequestParam int pageNum,int pageSize,@PathVariable Integer state){
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = adminService.getArticleList(state);
        PageInfo pageInfo = new PageInfo(articleList);
        return Result.success(pageInfo);
    }

    @GetMapping("/admin/question/list/{state}")
    public Result questionList(@RequestParam int pageNum,int pageSize,@PathVariable Integer state){
        PageHelper.startPage(pageNum,pageSize);
        List<Question> questionList = adminService.getQuestionList(state);
        PageInfo pageInfo = new PageInfo(questionList);
        return Result.success(pageInfo);
    }

    @GetMapping("/admin/resource/list/{state}")
    public Result resourceList(@RequestParam int pageNum,int pageSize,@PathVariable Integer state){
        PageHelper.startPage(pageNum,pageSize);
        List<com.kang.entity.Resource> resourceList = adminService.getResourceList(state);
        PageInfo pageInfo = new PageInfo(resourceList);
        return Result.success(pageInfo);
    }


    @PostMapping("/admin/works/pass")
    public Result checkPass(@RequestBody Inform inform){
        inform.setApplyState(1);
        int apply=adminService.Apply(inform);
        if(apply==1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @PostMapping("/admin/works/reject")
    public Result reject(@RequestBody Inform inform){
        inform.setApplyState(0);
        int apply=adminService.Apply(inform);
        if(apply==1){
            return Result.success();
        }else {
            return Result.error();
        }
    }





}
