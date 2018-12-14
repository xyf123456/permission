package com.tt.permission;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationTest {

//    最简单的Realm
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
//        simpleAccountRealm.addAccount("admin", "123");
//        simpleAccountRealm.addAccount("admin", "123","admin");
        simpleAccountRealm.addAccount("admin", "123","admin","user");
    }

    @Test
    public void testAuthentication() {
//        1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);


//        2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
        subject.login(token);

        System.out.println("isAuthenticated(是否认证):"+subject.isAuthenticated());

//        检查登录用户是否具备拥有的角色
//        subject.checkRole("admin");
        subject.checkRoles("admin","user");

//        退出登录后再次检查
//        subject.logout();

//        System.out.println("isAuthenticated(是否认证):"+subject.isAuthenticated());
    }
}
