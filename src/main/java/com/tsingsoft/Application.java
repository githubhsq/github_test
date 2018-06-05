package com.tsingsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * 程序入口.
 * 
 * @author L.Yang
 * @version v1.0 2017年6月2日 上午11:38:00
 */
@SpringBootApplication
// @EnableAsync
//@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
