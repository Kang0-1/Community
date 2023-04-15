package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.Article;
import com.kang.entity.History;
import com.kang.entity.vo.QueryArticleVo;
import com.kang.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Article)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryArticleVo queryArticleVo){
        return Result.success(articleService.getListByParam(queryArticleVo));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Article article){
        return Result.success(articleService.saveArticle(article));
    }

    @GetMapping("/associated/{ids}")
    public Result articles(@PathVariable String ids){
        return Result.success(articleService.getListByIds(ids));
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody History history){
        return Result.success(articleService.getArticleInfo(history));
    }

    /**
     * 查询各种状态的article的数量
     * 暂存0，审核1，审核未通过2，通过审核3
     * @param uid author_id 作者id
     * @return List
     */
    @GetMapping("/num/{uid}")
    public Result num(@PathVariable Long uid){
        return Result.success(articleService.getNumList(uid));
    }

    @GetMapping("/del/{aid}")
    public Result del(@PathVariable String aid){
        int i=articleService.delArticle(aid);
        if(i>0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @GetMapping("/info/{aid}")
    public Result info(@PathVariable String aid){
        return Result.success(articleService.getArticle(aid));
    }

}


