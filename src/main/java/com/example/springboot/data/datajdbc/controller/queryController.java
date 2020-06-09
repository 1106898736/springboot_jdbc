package com.example.springboot.data.datajdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@ResponseBody
@Controller
public class queryController {
    @Autowired
    JdbcTemplate jdbctemplate;

    @GetMapping("/query")
    @ResponseBody
    public Map query(){
        List<Map<String, Object>> map=jdbctemplate.queryForList("select * from swf");
        return map.get(0);
    }
}
