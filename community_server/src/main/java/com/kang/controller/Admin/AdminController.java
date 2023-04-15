package com.kang.controller.Admin;

import com.kang.controller.Admin.service.AdminService;
import com.kang.domain.Result;
import com.kang.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author kang
 * @description 后台管理
 * @date 2023/4/15 15:15
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 超级管理员
     */



    /**
     * 普通管理员
     */

    @PostMapping("/admin/login")
    public Result login(@RequestBody User user){
        Map<Object,Object> result=adminService.goLogin(user);
        if(result.containsKey("error")){
            return Result.error(result.get("error").toString());
        }else {
            return Result.success(result);
        }
    }

    @GetMapping("/admin/user/list")
    public Result list(){

    }


}
