package com.kang.controller;

import com.kang.entity.Attention;
import com.kang.domain.Result;
import com.kang.entity.Attention;
import com.kang.service.AttentionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Attention)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@RestController
@RequestMapping("attention")
public class AttentionController {

    @Resource
    private AttentionService attentionService;

    @PostMapping("/judge")
    public Result judge(@RequestBody Attention attention){
        int i=attentionService.judgeAttention(attention);
        if(i==1){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    /**
     * 查询关注列表
     * type: 0->查询关注列表 ；1->查询粉丝列表
     */
    @GetMapping("/list/{type}/{id}")
    public Result list(@PathVariable int type,@PathVariable Long id){
        return Result.success(attentionService.getAttentionList(type,id));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Attention attention){
        int i=attentionService.saveAttention(attention);
        if(i==1){
            return Result.success("关注成功!");
        }else {
            return Result.error("关注失败!");
        }
    }

    @PostMapping("/del")
    public Result del(@RequestBody Attention attention){
        int i=attentionService.delAttention(attention);
        if(i==1){
            return Result.success("已取消关注!");
        }else {
            return Result.error();
        }
    }

}


