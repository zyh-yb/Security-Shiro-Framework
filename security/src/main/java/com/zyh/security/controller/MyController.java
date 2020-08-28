package com.zyh.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: 云
 * @date: 2020/8/28 10:17
 * @version: 1.0
 */
@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello");
        return "index";
    }

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }
    @RequestMapping("/upd")
    public String upd(){
        return "user/upd";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
//    @RequestMapping("login")
//    public String login(String username,String password,Model model){
//        //获取当前的用户
//        Subject subject = SecurityUtils.getSubject();
//        //封装用户的登录数据
//        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
//
//        try {
//            subject.login(token);//执行登录方法，如果没有异常就是ok的
//            return "index";
//        } catch (UnknownAccountException e) { //用户名不存在
//            model.addAttribute("msg","用户名错误");
//            return "login";
//        } catch (IncorrectCredentialsException e) { //用户名不存在
//            model.addAttribute("msg","密码错误");
//            return "login";
//        }
//    }

    @ResponseBody
    @RequestMapping("/uunauthorized")
    public String uunauthorized(){
        return "没有权限访问";
    }

}
