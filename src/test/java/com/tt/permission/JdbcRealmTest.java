package com.tt.permission;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcRealmTest {

    HikariDataSource hikariDataSource = new HikariDataSource();

    {
        hikariDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/permission");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
    }

    @Test
    public void testAuthentication() {
//        创建JdbcRealm对象
        JdbcRealm jdbcRealm = new JdbcRealm();
//        JdbcRealm设置数据源
        jdbcRealm.setDataSource(hikariDataSource);
//        JdbcRealm开启权限验证
        jdbcRealm.setPermissionsLookupEnabled(true);

//        1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
//        SecurityManager通过设置不同类型的Realm来进行主体的验证
        defaultSecurityManager.setRealm(jdbcRealm);


//        2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
//        获取认证的主体
        Subject subject = SecurityUtils.getSubject();
//        通过用户名和密码获取UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken("xyf", "123456");
//        通过token进行登录认证
        subject.login(token);
//        打印出是否认证成功
        System.out.println("isAuthenticated(是否认证):" + subject.isAuthenticated());
//        检查角色
//        subject.checkRole("admin");
        subject.checkRoles("admin","user");
//        检查权限
//        subject.checkPermission("user:add");
        subject.checkPermissions("user:add","user:delete");
    }
}
