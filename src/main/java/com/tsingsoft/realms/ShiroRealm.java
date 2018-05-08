package com.tsingsoft.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;

public class ShiroRealm extends AuthenticatingRealm{

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo: " + authenticationToken.hashCode());
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (username.equals("hsq")){
            throw new UnknownAccountException("用户名不存在！");
        }
        if (username.equals("hhh")){
            throw new LockedAccountException("用户被锁定！");
        }
        Object principal = username;
        Object credentials = "123456";
        String realName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realName);
        return info;
    }
}
