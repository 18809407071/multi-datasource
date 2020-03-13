/**
 * Copyright (c) 2020, 中电万维公司 All rights reserved.
 * 中电万维公司 专有/保密源代码,未经许可禁止任何人通过任何渠道使用、修改源代码.
 */
package com.example.multidatasource.controller;

import com.example.multidatasource.entity.studbEntity.Student;
import com.example.multidatasource.entity.test1Entity.User;
import com.example.multidatasource.repository.studbRepository.StudentRepository;
import com.example.multidatasource.repository.test1Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName JpaTestController
 * @Description TODO
 * @Author caoti
 * @Date 2020/3/13 17:43
 * @since Jdk 1.8
 **/
@Controller
public class JpaTestController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @GetMapping("/studbJpaTest")
    public String studbJpaTest(){
        Student student = new Student();
        student.setId("111222");
        student.setName("zhangsan");
        student.setPassword("aabbcc");
        student.setSex("男");
        try{
            studentRepository.save(student);
            return "保存成功";
        }catch (Exception e){
            e.printStackTrace();
            return "保存失败:"+e.getCause().getMessage();
        }
    }

    @ResponseBody
    @GetMapping("/test1JpaTest")
    public String test1JpaTest(){
        User user = new User();
        user.setLogname("admin");
        user.setEmail("1001@qq.com");
        user.setPassword("abced");
        try{
            userRepository.save(user);
            return "保存成功";
        }catch (Exception e){
            e.printStackTrace();
            return "保存失败:"+e.getCause().getMessage();
        }
    }

}
