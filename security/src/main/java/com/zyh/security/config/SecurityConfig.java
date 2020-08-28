package com.zyh.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: 云
 * @date: 2020/8/28 10:42
 * @version: 1.0
 */
@EnableWebSecurity // 开启WebSecurity模式
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权
    //链式编程
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //拦截
        http.authorizeRequests()
                .antMatchers("/").permitAll() //谁都有可以访问首页
                .antMatchers("/add").hasAnyRole("boss");//访问add页需要boss权限

        //如果没有权限 去登录页面
        http.formLogin().loginPage("/toLogin");

        //防止网站攻击 get post
        http.csrf().disable(); //关闭csrf

        //用户注销  并跳转首页
        http.logout().logoutSuccessUrl("/");

        //开启记住我 功能     这里在前端勾选记住我 传 remember
        http.rememberMe().rememberMeParameter("remember");

    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())//这里 new 加密下面用
                //这里是写死的，一般是数据库取值 用户名-密码-角色
                // 这里的密码必须是加密的，不然会报500的错误，自认为不安全的
                .withUser("123").password(new BCryptPasswordEncoder().encode("123")).roles("boss")
                .and() // 用 and() 可以无限 链接/追加
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123")).roles("");

    }

}
