package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.User;
import com.kang.entity.vo.LoginVo;
import com.kang.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@RestController
@RequestMapping
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/imgCode")
    public Result imgCode() {
        return Result.success(userService.getImgCode());
    }

    @PostMapping("/emailCode")
    public Result emailCode(@RequestBody Map<String, String> map) {
        Map<Object, Object> res = userService.getEmailCode(map.get("email"));
        if (res != null) {
            return Result.success(res);
        }
        return Result.error();
    }


    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        return userService.login(loginVo);
    }

}


