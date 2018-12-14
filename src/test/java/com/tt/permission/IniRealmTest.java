package com.tt.permission;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IniRealmTest {


    @Test
    public void testAuthentication() {

        IniRealm iniRealm = new IniRealm("classpath:user.ini");
//        1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //        SecurityManager通过设置不同类型的Realm来进行主体的验证
        defaultSecurityManager.setRealm(iniRealm);


//        2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
//        获取认证主体
        Subject subject = SecurityUtils.getSubject();
//        通过用户名和密码获取UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken("user", "654321");
//        登录认证
        subject.login(token);
//        打印出是否认证成功
        System.out.println("isAuthenticated(是否认证):" + subject.isAuthenticated());
//        检查用户拥有的角色
        subject.checkRole("user");
//        检查角色拥有的权限
        subject.checkPermission("user:add");

    }
}
