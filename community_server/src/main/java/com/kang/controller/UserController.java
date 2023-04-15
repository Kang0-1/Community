package com.kang.controller;

import com.kang.domain.Result;
import com.kang.entity.User;
import com.kang.entity.vo.LoginVo;
import com.kang.entity.vo.PassVo;
import com.kang.entity.vo.PayVo;
import com.kang.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (User)表控制层
 * @author kang
 * @since 2023-02-26 15:03:00
 */
@RestController
@RequestMapping
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 图片验证码
     */
    @GetMapping("/imgCode")
    public Result imgCode() {
        return Result.success(userService.getImgCode());
    }

    /**
     * 邮箱登录,注册时发送验证码
     * @param map 接受前端返回的表单数据(email:xxx@qq.com)
     */
    @PostMapping("/emailCode")
    public Result emailCode(@RequestBody Map<String, String> map) {
        Map<Object, Object> res = userService.getEmailCode(map.get("email"));
        if (res != null) {
            return Result.success(res);
        }
        return Result.error();
    }

    /**
     * 用户登录，注册
     * @param loginVo 登录参数
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        return userService.login(loginVo);
    }

    /**
     * 登出
     */
    @GetMapping ("/logout/{uid}")
    public Result logout(@PathVariable String  uid){
        return Result.success();
    }

    /**
     * 查询uid的用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/user/{id}")
    public Result userInfo(@PathVariable Long id){
        User user=new User();
        user.setUserId(id);
        return Result.success(userService.selectUserByParam(user));
    }

    @PostMapping("/user/save")
    public Result save(@RequestBody User user){
        return Result.success(userService.saveUser(user));
    }

    /**
     * 查询用户的关注数，粉丝数和收获点赞数
     * @param id 用户id
     * @return Vo SomeNum
     */
    @GetMapping("/some/{id}")
    public Result someNum(@PathVariable Long id){
        return Result.success(userService.getSomeNum(id));
    }


    @PostMapping("/user/pay")
    public Result pay(@RequestBody PayVo payVo){
        int i=userService.goPay(payVo);
        if(i==1){
            return Result.success("支付成功!");
        }
        if(i==0){
            return Result.error("您的T币余额不足!");
        }
        if(i==-1){
            return Result.error("支付密码错误!");
        }
        return Result.error("未知错误"+i);
    }

    @GetMapping("/user/balance/{id}")
    public Result balance(@PathVariable Long id){
        return Result.success(userService.getBalance(id));
    }

    @PostMapping("/user/pass/save")
    public Result savePass(@RequestBody PassVo passVo){
        //0:登录密码添加，1登录密码修改，2支付密码添加，3支付密码修改
        int code=userService.change(passVo);
        if(code==0){
            return Result.error("原密码错误!");
        }else {
            return Result.success("修改密码成功!");
        }
    }




}


