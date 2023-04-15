package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.Resource;
import com.kang.entity.vo.QueryResourceVo;
import com.kang.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (Resource)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryResourceVo queryResourceVo){
        return Result.success(resourceService.getListByParam(queryResourceVo));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Resource resource){
        int i=resourceService.saveResource(resource);
        if(i>0){
            return Result.success("审核已提交!");
        }else {
            return Result.error("提交失败!");
        }
    }

    /**
     * 通过 idList 查找资源列表 (视频相关联)
     * @param ids ResourceIdList
     * @return ResourceList
     */
    @GetMapping("/associated/{ids}")
    public Result associated(@PathVariable String ids){
        return Result.success(resourceService.getListByIds(ids));
    }

    @GetMapping("/num/{uid}")
    public Result num(@PathVariable Long uid){
        return Result.success(resourceService.getNumList(uid));
    }

    @GetMapping("/del/{rid}")
    public Result del(@PathVariable String rid){
        int i=resourceService.delResource(rid);
        if(i>0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @GetMapping("/info/{rid}")
    public Result info(@PathVariable String rid){
        return Result.success(resourceService.getResource(rid));
    }

}


