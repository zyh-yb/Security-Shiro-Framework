package com.zyh.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: 云
 * @date: 2020/8/27 10:13
 * @version: 1.0
 */
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean:3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager")DefaultWebSecurityManager getDefaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(getDefaultWebSecurityManager);

        //添加shiro的内置过滤器
        /**
         * anon: 无需认证就可以访问
         * authc: 必须认证才能访问
         * user: 必须拥有 记住我 功能才能用
         * perms: 拥有对某个资源的权限才能访问
         * role: 拥有某个角色权限才能访问
         */
        //拦截设置 对所有页面进行访问过滤，并赋予权限
        //如果判断没有权限，跳转提示页面
        Map<String ,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/add","perms[boss]");

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录请求 判断没有登录先去登录
        bean.setLoginUrl("/toLogin");
        //没有权限提示页面
        bean.setUnauthorizedUrl("/uunauthorized");

        return bean;
    }

    //DafaultWebSecurityManager:2
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联 userRealm
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    //创建 realm 对象 需要自定义类：1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合ShiroDialect Shiro 和 thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }













}
