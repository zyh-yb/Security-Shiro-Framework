package com.zyh.shiro.config;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * @author: 云
 * @date: 2020/8/27 10:12
 * @version: 1.0
 */

public class UserRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权");

/**
 *      //数据库获取数据 模拟
 *      //获取当前登录对象
 *      Subject subject = SecurityUtils.getSubject();
 *      User currenUser = (User) subject.getPrincipal(); // 取出 User对象 强转
 *      info.addStringPermission(currenUser.get角色);
 *      return info;
 */

        //权限赋予  这样全部登录的都有这个权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("boss");


        //return null;
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证");

/**
 *       模拟数据库获取
 *
 *      UsernamePasswordToken userToken = (UsernamePasswordToken) token;
 *      User user = userService.getUser(条件查询);
 *       if(user == null){ //没有这个人
 *           return null;
 *       }
 *
 *       //前端 显示/隐藏   看index页面 的登录 两个字
 *       Subject currentSubject = SecurityUtils.getSubject();
 *       Session session = currentSubject.getSession();
 *       session.setAttribute("loginUser",user);
 *
 *       //这里注意 因为是数据库获取所以也要获取 权限/角色 传给上面的授权操作
 *       就需要把 User 对象传递                 ↓↓↓重点
 *       return new SimpleAuthenticationInfo(user,pass,"");
 */
        //这个地方是写死的用户，实际应该是数据库获取
        String user = "123";
        String pass = "123";

        //前端 显示/隐藏   看index页面 的登录 两个字
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        //这里是写死的 效果不明显 登录 两字不显示，从数据库获取的 可能是null 登录 两字显示
        session.setAttribute("loginUser",null);

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if (!userToken.getUsername().equals(user))
            return null;//抛出异常

        //密码认证 shiro自己做
        return new SimpleAuthenticationInfo("", pass, "");

    }
}
