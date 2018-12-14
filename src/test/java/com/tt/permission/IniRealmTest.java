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
        defaultSecurityManager.setRealm(iniRealm);


//        2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("user", "654321");
        subject.login(token);

        System.out.println("isAuthenticated(是否认证):" + subject.isAuthenticated());
//        检查用户拥有的角色
        subject.checkRole("user");
//        检查角色拥有的权限
        subject.checkPermission("user:add");

    }
}
