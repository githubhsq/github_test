package com.tsingsoft.realms;

import com.sun.tools.classfile.Opcode;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm{

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("firstRealm: " + authenticationToken.hashCode());
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
             credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        }else  if (username.equals("user")){
             credentials = "098d2c478e9c11555ce2823231e02ec1";
        }

        String realName = getName();
        ByteSource salt = ByteSource.Util.bytes(username);
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,salt,realName);
        return info;
    }

    public static void main(String args[]){
        String algorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("admin");
        int hashIterations = 1024;
        Object result = new SimpleHash(algorithmName,credentials,salt,hashIterations);
        System.out.println(result);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if (principal.equals("admin")){
            roles.add("admin");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }
}
