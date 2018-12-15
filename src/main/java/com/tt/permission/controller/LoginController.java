package com.tt.permission.controller;

import com.tt.permission.pojo.User;
import com.tt.permission.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: com.tt.permission.controller.LoginController
 * @Description: 登录控制器
 * @Author: Administrator
 * @CreateDate: 2018/12/14 8:28
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    /**
     * @ Description:跳转到登录页面
     * @params: * @Param:
     * @return:java.lang.String
     **/
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/dologin")
    @ResponseBody
    public Object dologin(User user) throws Exception {
//        声明map集合来存储登录时的信息
        Map<String, Object> resultMap = new HashMap<>();
        //获取登录认证的主体
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());

        try {
            subject.login(token);
            resultMap.put("flag", "ok");
        }catch (Exception e){
            if (!Strings.isEmpty(e.getMessage())){
                e.printStackTrace();
            }
            resultMap.put("flag", "ng");
        }

        return resultMap;
        /*if (userService.login(user)) {
            return "登录成功";
        }
        return "登录失败";*/
    }

    /**
     * 跳转【系统首页】页面
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/logout")
    public String logout() {
        // 1、获取Subject
        Subject subject = SecurityUtils.getSubject();

        // 2、执行注销
        try {
            subject.logout();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return "redirect:login";
        }
    }
}

