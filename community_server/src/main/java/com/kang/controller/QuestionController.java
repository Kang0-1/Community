package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.History;
import com.kang.entity.Question;
import com.kang.entity.vo.QueryQuestionVo;
import com.kang.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Question)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@RestController
@RequestMapping("question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryQuestionVo queryQuestionVo){
        return Result.success(questionService.getListByParam(queryQuestionVo));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Question question){
        int i=questionService.saveQuestion(question);
        if (i > 0) {
            return Result.success("审核已提交!");
        }else {
            return Result.error("提交失败!");
        }
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody History history){
        return Result.success(questionService.getQuestionInfo(history));
    }

    /**
     * 查询各种状态的question的数量
     * 暂存0，审核1，审核未通过2，通过审核3
     * @param uid author_id 作者id
     * @return List
     */
    @GetMapping("/num/{uid}")
    public Result num(@PathVariable Long uid){
        return Result.success(questionService.getNumList(uid));
    }

    @GetMapping("/del/{qid}")
    public Result del(@PathVariable String qid){
        int i=questionService.delQuestion(qid);
        if(i>0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @GetMapping("/info/{qid}")
    public Result info(@PathVariable String qid){
        return Result.success(questionService.getQuestion(qid));
    }

    @PostMapping("/accept")
    public Result accept(@RequestBody Question question){
        return Result.success(questionService.acceptAnswer(question));
    }


}


