package com.example.week7.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DbConfig {

    private DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    /*@EventListener(ApplicationReadyEvent.class)
    public void init() throws SQLException {
        String sql =
        "CREATE table cars(car_id int, mark varchar(255), model varchar(255), color varchar(255), production_year varchar(255), PRIMARY KEY(car_id) ) ";
        getJdbcTemplate().update(sql);
        dataSource.getConnection().close();
    }*/
}
