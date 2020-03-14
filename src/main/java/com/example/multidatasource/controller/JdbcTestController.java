package com.example.multidatasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author caoti
 * @Date 2020/3/13 16:11
 * @since Jdk 1.8
 **/
@Controller
public class JdbcTestController {

    @Autowired
    @Qualifier("studbJdbcTemplate")
    private JdbcTemplate studbJdbcTemplate;

    @Autowired
    @Qualifier("test1JdbcTemplate")
    private JdbcTemplate test1JdbcTemplate;

    @ResponseBody
    @GetMapping("/jdbctest")
    public Object jdbcTest(){
        Map<String,Object> result = new HashMap<>();
        result.put("studb-student:",studbJdbcTemplate.queryForList("select * from student"));
        result.put("test1-user:",test1JdbcTemplate.queryForList("select * from user"));
        return result;
    }
}
