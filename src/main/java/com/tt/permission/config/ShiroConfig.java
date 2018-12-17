package com.tt.permission.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: com.tt.permission.config.ShiroConfig
 * @Description: Shiro配置
 * Shiro本身无法知道所持有令牌的用户是否合法，因为除了项目的设计人员恐怕谁都无法得知。
 * 因此Realm是整个框架中为数不多的必须由设计者自行实现的模块，当然Shiro提供了多种实现
 * 的途径，本文只介绍最常见也最重要的一种实现方式——数据库查询。
 * @Author: Administrator
 * @CreateDate: 2018/12/14 9:39
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
@Configuration
public class ShiroConfig {

    /**
     * @ Description:创建ShiroFilterFactoryBean
     * @params: * @Param: securityManager
     * @return:org.apache.shiro.spring.web.ShiroFilterFactoryBean
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置登录跳转页面
        shiroFilterFactoryBean.setLoginUrl("/login");

        // 拦截器
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 从上向下顺序判断
        filterMap.put("/static/**", "anon");
        filterMap.put("/templates/**", "anon");
        filterMap.put("/biz/**", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/easyui/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/echarts/**", "anon");
        filterMap.put("/dologin", "anon");

        // 配置退出过滤器
        filterMap.put("/logout", "logout");

        // 配置授权过滤器

        // 过滤链定义，从上向下顺序执行，一般将/*放在最下边
//        该过滤器下的页面必须验证后才能访问,它是Shiro内置的一
//        个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
        filterMap.put("/**", "authc");

        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    // 创建DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 关联Realm
        defaultWebSecurityManager.setRealm(myRealm);

        //        创建HashedCredentialsMatcher,设置加密匹配项
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(1);
        matcher.setHashAlgorithmName("md5");
        myRealm.setCredentialsMatcher(matcher);

        return defaultWebSecurityManager;
    }

    // 创建Realm
    @Bean(name = "myRealm")
    public MyRealm myRealm() {
        return new MyRealm();
    }
}
