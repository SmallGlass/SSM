package com.kk.controller;


import com.kk.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by yzb on 2018/4/1.
 */
//登录跳转
@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);// 日志文件
    //登录表单处理
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(User user, HttpServletRequest request) throws Exception {
        String rand = (String)request.getSession().getAttribute("rand");
        String captcha = WebUtils.getCleanParam(request, "captcha");
        if(!StringUtils.equals(rand, captcha)){
            request.setAttribute("message_login", "验证码不正确");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX + "/login.jsp";
        }
        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
                user.getPassword());
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();

        try {
            //在调用了login方法后，SecurityManager会收到AuthenticationToken，并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时，它会走到MyRealm.doGetAuthenticationInfo()方法中，具体验证方式详见此方法
            subject.login(token);
        }catch(UnknownAccountException uae){
            request.setAttribute("message_login", "用户名/密码不正确");
        }catch(IncorrectCredentialsException ice){
            request.setAttribute("message_login", "用户名/密码不正确");
        }catch(LockedAccountException lae){
            request.setAttribute("message_login", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            request.setAttribute("message_login", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            ae.printStackTrace();
            request.setAttribute("message_login", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(subject.isAuthenticated()){
            if (subject.hasRole("admin")) {
                return "/admin/showStudent";
            } else if (subject.hasRole("teacher")) {
                return "/teacher/showCourse";
            } else if (subject.hasRole("student")) {
                return "/student/showCourse";
            }
        }else{
            token.clear();
            return InternalResourceViewResolver.FORWARD_URL_PREFIX + "/login.jsp";
        }
        return null;
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        String currentUser = (String)session.getAttribute("currentUser");
        SecurityUtils.getSubject().logout();
        System.out.println("用户[" + currentUser + "]已登出");
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/login.jsp";
    }
}
