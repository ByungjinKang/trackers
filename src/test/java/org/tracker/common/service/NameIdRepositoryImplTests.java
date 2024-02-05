package org.tracker.common.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class NameIdRepositoryImplTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testFindIdByName() {
        String sql = "SELECT id FROM CATEGORY WHERE name = ?";
        Long id = jdbcTemplate.queryForObject(sql, new Object[]{"교통비"}, Long.class);
        System.out.println("Retrieved ID: " + id);
    }
}