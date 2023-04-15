package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.History;
import com.kang.entity.Video;
import com.kang.entity.vo.QueryVideoVo;
import com.kang.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (Video)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@RestController
@RequestMapping("video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryVideoVo queryVideoVo){
        return Result.success(videoService.getListByParam(queryVideoVo));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Video video){
        Map<Object,Object> resultMap=videoService.saveVideo(video);
        if(resultMap.containsKey("success")){
            return Result.success(resultMap);
        }
        return Result.error(resultMap.get("error"));
    }

    /**
     * 点击获取视频详情，并添加历史记录
     */
    @PostMapping("/detail")
    public Result detail(@RequestBody History history){
        return Result.success(videoService.getVideoInfo(history));
    }

    @GetMapping("/info/{vid}")
    public Result info(@PathVariable String vid){
        return Result.success(videoService.getVideo(vid));
    }

    @GetMapping("/num/{uid}")
    public Result num(@PathVariable Long uid){
        return Result.success(videoService.getNumList(uid));
    }

    @GetMapping("/del/{vid}")
    public Result del(@PathVariable String vid){
        int i=videoService.delVideo(vid);
        if(i>0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

}


