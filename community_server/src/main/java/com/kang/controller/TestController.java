package com.kang.controller;

import com.kang.domain.Mail;
import com.kang.utils.MailUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author kang
 * @description TODO
 * @date 2023/3/23 18:00
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private MailUtil mailUtil;

    @RequestMapping("/sendEmail")
    public Boolean sendEmail( Mail mail){
        return mailUtil.sendEmail(mail);
    }

    @RequestMapping("/testStr")
    public String testStr(){
        return "成功";
    }

}
