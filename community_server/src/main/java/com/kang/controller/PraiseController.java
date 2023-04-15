package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.Praise;
import com.kang.service.PraiseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Praise)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@RestController
@RequestMapping("praise")
public class PraiseController {

    @Resource
    private PraiseService praiseService;

    @PostMapping("/num")
    public Result num(@RequestBody Praise praise){
        return Result.success(praiseService.getPraiseNum(praise));
    }

    @PostMapping("/del")
    public Result del(@RequestBody Praise praise){
        int i=praiseService.delPraise(praise);
        if(i==1){
            return Result.success("点赞已取消");
        }else {
            return Result.error();
        }
    }

    @PostMapping("/save")
    public Result save(@RequestBody Praise praise){
        int i=praiseService.savePraise(praise);
        if(i==1){
            return Result.success("点赞成功!");
        }else {
            return Result.error("点赞失败!");
        }
    }

}


