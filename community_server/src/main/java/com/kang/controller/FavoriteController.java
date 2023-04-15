package com.kang.controller;


import com.kang.domain.Result;
import com.kang.entity.Favorite;
import com.kang.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*** (Favorite)表控制层
 ** @author makejava
* @since 2023-02-26 15:02:59
*/
@RestController
@RequestMapping("favorite")
public class FavoriteController {

    /**
     * Favorite 收藏夹 -(名词)
      */

    @Resource
    private FavoriteService favoriteService;

    @GetMapping("/list/{uid}")
    public Result list(@PathVariable Long uid){
        return Result.success(favoriteService.getList(uid));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Favorite favorite){
        int i = favoriteService.saveFavorite(favorite);
        if (i>0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

}


