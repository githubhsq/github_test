package com.tsingsoft.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm{

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("secodeRealm: " + authenticationToken.hashCode());
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (username.equals("hsq")){
            throw new UnknownAccountException("用户名不存在！");
        }
        if (username.equals("hhh")){
            throw new LockedAccountException("用户被锁定！");
        }
        Object principal = username;
        Object credentials = null;
        if (username.equals("admin")){
             credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
        }else  if (username.equals("user")){
             credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
        }

        String realName = getName();
        ByteSource salt = ByteSource.Util.bytes(username);
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("secondrealm",credentials,salt,realName);
        return info;
    }

    public static void main(String args[]){
        String algorithmName = "SHA1";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("user");
        int hashIterations = 1024;
        Object result = new SimpleHash(algorithmName,credentials,salt,hashIterations);
        System.out.println(result);
    }
}
