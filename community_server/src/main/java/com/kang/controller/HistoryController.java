package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.History;
import com.kang.service.HistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (History)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@RestController
@RequestMapping("history")
public class HistoryController {

    @Resource
    private HistoryService historyService;

    @GetMapping("/list/{uid}")
    public Result getHistoryList(@PathVariable Long uid){
        return Result.success(historyService.getHistoryList(uid));
    }

    //  Map映射结果
    @PostMapping("/del")
    public Result del(@RequestBody Map<String ,List<String>> listMap){
        List<String> idList= listMap.get("idList");
        int i=historyService.delHistory(idList);
        if(i>0){
            return Result.success("删除成功!");
        }
        return Result.error();
    }

}


