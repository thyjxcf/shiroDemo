package com.hzgg.test.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Designed By luf
 *
 * @author luf
 * @date 2019/12/3 10:22
 */
@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println("hello");
        return "hello";
    }
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model mode) {
        mode.addAttribute("name", "programer");
        return "success";
    }

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }
    @RequestMapping("/update")
    public String upate() {
        return "user/update";
    }
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "user/login";
    }
    @RequestMapping("/login")
    public String login(String name,String password ,Model model) {
        /**
         * 使用shiro 完成认证操作
         * 拿到subject
         */
        //获取到subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name,password);
        //3.执行登录操作
        try{
            subject.login(usernamePasswordToken);
            //没有任何异常就登录成功
            return "redirect:/testThymeleaf";
        }catch (UnknownAccountException e){
//            e.printStackTrace();
            //登录失败 指的是账号错误
            model.addAttribute("msg", "用户名不存在");
            return "redirect:/toLogin";
        }catch (IncorrectCredentialsException e){
//            e.printStackTrace();
            //登录失败 指的是账号错误
            model.addAttribute("msg", "密码错误");
            return "redirect:/toLogin";
        }

    }
}
