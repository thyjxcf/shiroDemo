package com.hzgg.test.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Designed By luf
 * 声明为配置类
 * @author luf
 * @date 2019/12/3 14:51
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        filterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器
        //一些常用的内置过滤器 。
//        anon：无需认证登录也可以访问
//        authc：必须认证才可以访问
//        user:如果使用rememberMe功能可以直接访问
//        perms:该资源必须得到资源权限才可以访问
//        role：该资源必须得到角色权限才可以访问

        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/add", "authc");
//        filterMap.put("/update", "authc");
        filterMap.put("/testThymeleaf", "anon");
        filterMap.put("/login", "anon");
        //授权拦截器 当授权拦截器拦截后 会自动跳到未授权页面。
        filterMap.put("/add", "perms[user:add]");
        filterMap.put("/update", "perms[user:update]");
        filterMap.put("/*", "authc");
        //修改跳转页面 不是login.ftl
        filterFactoryBean.setLoginUrl("/toLogin");
        filterFactoryBean.setUnauthorizedUrl("/noauth");
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return filterFactoryBean;
    }
    /**
     * 创建 DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("uesrRealm") UesrRealm uesrRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(uesrRealm);
        return securityManager;
    }
    /**
     * 创建 Realm
     */
    @Bean(name="uesrRealm")
    public UesrRealm getRealm() {
        return  new UesrRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect() {
        return  new ShiroDialect();
    }
}
