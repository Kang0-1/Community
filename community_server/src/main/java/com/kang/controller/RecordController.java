package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.Record;
import com.kang.service.RecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Record)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@RestController
@RequestMapping("record")
public class RecordController {

    @Resource
    private RecordService recordService;

    @PostMapping("/judge")
    public Result judge(@RequestBody Record record){
        int i = recordService.getRecordList(record).size();
        if(i>0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @GetMapping("/list/{uid}")
    public Result list(@PathVariable Long uid){
        Record record=new Record();
        record.setAuthorId(uid);
        return Result.success(recordService.getRecordList(record));
    }


}


