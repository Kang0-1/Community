package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.Answer;
import com.kang.service.AnswerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Answer)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@RestController
@RequestMapping("answer")
public class AnswerController {

    @Resource
    private AnswerService answerService;

    @PostMapping("/save")
    public Result save(@RequestBody Answer answer){
        return Result.success(answerService.saveAnswer(answer));
    }

    @PostMapping("/list")
    public Result list(@RequestBody Answer answer){
        return Result.success(answerService.getAnswerList(answer));
    }

}


