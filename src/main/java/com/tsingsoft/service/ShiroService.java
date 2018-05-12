package com.tsingsoft.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;

import java.util.Date;

public class ShiroService {

    @RequiresRoles({"admin"})
    public void testMethod(){
        System.out.println("test Method："+new Date());
        System.out.println(SecurityUtils.getSubject().getSession().getAttribute("key"));
    }
}
