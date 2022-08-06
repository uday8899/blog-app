package com.project.blogapp;

import com.project.blogapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){
        String className = this.userRepository.getClass().getName();
       String pkgName =  this.userRepository.getClass().getPackageName();
        System.out.println(className);
        System.out.println(pkgName);
    }
}
