package com.duing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    // 此时接收的是 localhost:8080/login的get请求地址  跳转到登录页面中
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // 接收的是  登录表单中的post请求及其参数  登录后跳转到首页
    // 如果使用spring-security  需要注释这部分代码
//    @PostMapping("/login")
//    public String login(@RequestParam("username")String username,
//                        @RequestParam("password")String password,
//                        Map<String,Object> map, HttpSession session){
//        // 只要用户名不为空  且密码符合要求
//        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
//            // 用session存储登录状态
//            session.setAttribute("loginUser",username);
//            // 登录成功  跳转首页
//            return "redirect:/";
//        }
//
//        return "login";
//    }
}
