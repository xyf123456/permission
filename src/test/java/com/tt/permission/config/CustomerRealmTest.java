package com.tt.permission.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRealmTest {

    @Test
    public void testAuthentication() {
//        创建CustomerRealm对象
        CustomerRealm customerRealm = new CustomerRealm();


//        1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
//        SecurityManager通过设置不同类型的Realm来进行主体的验证
        defaultSecurityManager.setRealm(customerRealm);

        //        创建HashedCredentialsMatcher
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(1);
        matcher.setHashAlgorithmName("md5");
        customerRealm.setCredentialsMatcher(matcher);

//        2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
//        获取认证的主体
        Subject subject = SecurityUtils.getSubject();
//        通过用户名和密码获取UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
//        通过token进行登录认证
        subject.login(token);
//        打印出是否认证成功
        System.out.println("isAuthenticated(是否认证):" + subject.isAuthenticated());
//        检查角色
//        subject.checkRole("admin");
//        检查权限
//        subject.checkPermission("/permission");
//        subject.checkPermissions("user:add","user:delete");
    }

}