package com.github.sergeyskotarenko.contacts.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    private final JdbcTemplate jdbcTemplate;

    public TestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("test")
    public ResponseEntity test() {
        List<Map<String, Object>> contacts = jdbcTemplate.queryForList("select * from contacts;");
        return ResponseEntity.ok(contacts);
    }
}
