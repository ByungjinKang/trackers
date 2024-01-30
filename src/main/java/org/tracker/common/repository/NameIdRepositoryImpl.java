package org.tracker.common.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class NameIdRepositoryImpl implements NameIdRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NameIdRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long findIdByName(String tableName, String name) {
        String sql = "SELECT id FROM " + tableName + " WHERE name = ?";
        log.info("SQL: " + sql);
//        return jdbcTemplate.queryForObject(sql, Long.class);
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, Long.class);
    }
}