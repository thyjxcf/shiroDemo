package com.hzgg.test.shiro;

import com.hzgg.test.domain.ShiroUser;
import com.hzgg.test.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Designed By luf
 * 自定义的userRealm
 * @author luf
 * @date 2019/12/3 14:55
 */
public class UesrRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加资源授权字符串
//        simpleAuthorizationInfo.addStringPermission("user:add");
        //获取当前登陆用户
        Subject subject  = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        ShiroUser dbUser = userService.findById(shiroUser.getId());
        simpleAuthorizationInfo.addStringPermission(dbUser.getPerms());
        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑 ");
        //假设数据库的用户名 密码
        String name = "show";
        String password = "zdsoft123";
        //编写shiro的判断逻辑，判断用户名密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        ShiroUser user = userService.findByName(token.getUsername());
        if (user == null) {
            //用户名不存在
            return null;//shiro底层就会抛出unKnowAccountException
        }
//        if (!token.getUsername().equals(name)) {
//            //用户名不存在
//            return null;//shiro底层就会抛出unKnowAccountException
//        }
        //2.判断密码

        return new SimpleAuthenticationInfo(user,token.getPassword(),"");
    }
}
