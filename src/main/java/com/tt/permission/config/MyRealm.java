package com.tt.permission.config;

import com.tt.permission.dao.ResourceRepository;
import com.tt.permission.dao.UserRepository;
import com.tt.permission.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @ClassName: com.tt.permission.config.MyRealm
 * @Description: 认证的主体
 * ShiroRealm父类AuthorizingRealm将获取Subject相关信息分成两步：
 * 获取身份验证信息（doGetAuthenticationInfo）及授权信息（doGetAuthorizationInfo）；
 * Subject是Shiro的核心对象，基本所有身份验证、授权都是通过Subject完成。
 * @Author: Administrator
 * @CreateDate: 2018/12/14 11:01
 * @UpdateUser: Administrator
 * @Version: 1.0
 **/
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * @ Description: 授权认证
     * @params: * @Param: principals
     * @return:org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前登录获得认证的用户
        User user = (User) principals.getPrimaryPrincipal();
        if (user != null){
            // 给资源授权
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            // 根据获得认证的 用户编号 查询该用户 具备的资源URL集合
            List<String> resourceurls  = resourceRepository.findUrlByUserid(user.getUserid());

            // 遍历集合，组装成满足授权过滤器过滤格式，并添加到资源信息中
            resourceurls.forEach(item -> info.addStringPermission(item));

            return info;
        }

        return null;
    }

    /**
     * @ Description: 身份认证
     * @params: * @Param: token
     * @return:org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 编写Shiro判断逻辑，判断账号和密码
        // 1、判断账号
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userRepository.findByUsername(token.getUsername());
        if (user == null) {
            // 账号错误，Shiro底层会抛出UnknownAccountException异常
            return null;
        }

        //2、判断密码
        // 认证后做授权处理，需要将获得认证的用户对象赋值给principal，授权处理时会用到
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
    }
}
