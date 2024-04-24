package com.gaorch.demo02.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    //有不知名bug,暂时注释，暂不实现
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void sendVerificationCode(String to, String code) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject("忘记密码 - 验证码");
//        message.setText("您的验证码是：" + code);
//        javaMailSender.send(message);
//    }
}
