package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.Inform;
import com.kang.entity.vo.QueryInformVo;
import com.kang.service.InformService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Inform)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:02:59
 */
@RestController
@RequestMapping("inform")
public class InformController {

    @Resource
    private InformService informService;

    /**
     * 查未读消息 接口
     */
    @PostMapping("/unread/num")
    public Result unread(@RequestBody QueryInformVo queryInformVo){
        return Result.success(informService.queryInformList(queryInformVo).size());
    }

    @PostMapping("/list")
    public Result list(@RequestBody QueryInformVo queryInformVo){
        return Result.success(informService.queryInformList(queryInformVo));
    }

    @PostMapping("/update/state")
    public Result updateState(@RequestBody List<String> ids){
        int i=informService.markRead(ids);
        if(i>0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<String> ids){
        int i=informService.delInforms(ids);
        if(i>0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

}


