package com.tsingsoft.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hsq on 2018/6/5.
 */
@RestController
public class DemoController {
    @RequestMapping("/hello")
    String hello(){
        return "this is a test";
    }
}
