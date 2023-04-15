package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.Collect;
import com.kang.service.CollectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Collect)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@RestController
@RequestMapping("collect")
public class CollectController {

    /**
     * 作品的收藏 -（行为）
     */

    @Resource
    private CollectService collectService;

    @PostMapping("/num")
    public Result num(@RequestBody Collect collect) {
        return Result.success(collectService.getCollectNum(collect));
    }

    @PostMapping("/del")
    public Result del(@RequestBody Collect collect) {
        int i = collectService.delCollect(collect);
        if (i > 0) {
            return Result.success("收藏已取消!");
        } else {
            return Result.error();
        }
    }

    @PostMapping("/save")
    public Result save(@RequestBody Collect collect) {
        int i = collectService.saveCollect(collect);
        if (i > 0) {
            return Result.success("收藏成功!");
        } else {
            return Result.error("收藏失败!");
        }
    }

    /**
     * 查看收藏夹里的 VideoList 或者 ArticleList
     * @param fid 收藏夹Id
     * @return VideoList || ArticleList
     */
    @GetMapping("/works/{fid}")
    public Result collect(@PathVariable String fid) {
        return Result.success(collectService.getCollectWorks(fid));
    }

}


