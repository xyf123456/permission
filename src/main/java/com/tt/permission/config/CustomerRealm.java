package com.tt.permission.config;

import com.tt.permission.dao.ResourceRepository;
import com.tt.permission.dao.UserRepository;
import com.tt.permission.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @ Author     ：xyf
 * @ Date       ：Created in 2018/12/15 0015下午 8:56
 * @ Description：
 * @ Modified By：xyf
 * @Version:
 */
public class CustomerRealm extends AuthorizingRealm {

    Map<String, String> userMap = new HashMap<>();

    {
        userMap.put("admin", "123456");
//        123456MD5加密处理（加密一次）
        userMap.put("admin", "e10adc3949ba59abbe56e057f20f883e");

//        123456+admin MD5加密处理（加密一次）
        userMap.put("admin", "a66abb5684c45962d887564f08346e8d");
        super.setName("customerRealm");
    }

    /**
     * @ Description: 授权认证
     * @params: * @Param: principals
     * @return:org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前登录获得认证的用户
        String username = (String) principals.getPrimaryPrincipal();
//       模拟从数据库或者缓存中获取用户拥有的角色
        Set<String> roles = findRolesByUsername(username);
        //       模拟从数据库或者缓存中获取角色拥有的权限
        Set<String> permissions = findPermissionsByRolename(username);
//        创建授权认证对象SimpleAuthorizationInfo
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        授权认证对象设置相关的角色
        authorizationInfo.setRoles(roles);
//        授权认证对象设置相关的角色拥有的权限
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * create by: xyf
     * description: 模拟从数据库或者缓存中获取用户拥有的权限
     * create time: 下午 9:06 2018/12/15 0015
     *
     * @Param: username
     * @return java.util.Set<java.lang.String>
     */
    private Set<String> findPermissionsByRolename(String username) {
        Set<String> sets = new HashSet<>();
        sets.add("user:add");
        sets.add("user:delete");
        sets.add("order:add");
        sets.add("order:delete");
        return sets;
    }


    /**
     * create by: xyf
     * description:  模拟从数据库或者缓存中获取用户拥有的角色
     * create time: 下午 9:05 2018/12/15 0015
     *
     * @Param: username
     * @return java.util.Set<java.lang.String>
     */
    private Set<String> findRolesByUsername(String username) {
        Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    /**
     * @ Description: 身份认证
     * @params: * @Param: token
     * @return:org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 编写Shiro判断逻辑，判断账号和密码
        String username = (String) authenticationToken.getPrincipal();
        // 1、判断账号
        String password = findPwdByUsername(username);
        //2、判断密码
        if (password == null) {
            return null;
        }
        // 认证后做授权处理，需要将获得认证的用户对象赋值给principal，授权处理时会用到
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("admin", password, "customerRealm");

//        在返回身份认证信息之前设置好salt(这里的salt为:admin)
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("admin"));
        return authenticationInfo;
    }

    /**
     * create by: xyf
     * description: 模拟从数据库获取用户的凭证
     * create time: 下午 8:59 2018/12/15 0015
     *
     * @return java.lang.String
     * @Param: username
     */
    private String findPwdByUsername(String username) {
        return userMap.get(username);
    }
}
