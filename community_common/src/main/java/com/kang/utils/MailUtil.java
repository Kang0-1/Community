package com.kang.utils;

import com.kang.domain.Mail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kang
 * @description TODO
 * @date 2023/3/23 15:56
 */
@Component
public class MailUtil {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.nickname}")
    private String nickname;

    @Value("${spring.mail.username}")
    private String from;

    public  Boolean sendEmail(Mail mail){
        //创建简单邮件信息
        SimpleMailMessage message=new SimpleMailMessage();
        //发件人
        message.setFrom(nickname+"<"+from+">");
        //收件人
        message.setTo(mail.getRecipient());
        //邮件标题
        message.setSubject(mail.getSubject());
        //邮件内容
        message.setText(mail.getContent());
        try{
            javaMailSender.send(message);
            return true;
        }catch (MailException e){
            e.printStackTrace();
            return false;
        }
    }
}
