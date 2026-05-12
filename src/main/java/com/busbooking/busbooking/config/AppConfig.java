package com.busbooking.busbooking.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class AppConfig {

//    @Bean
//    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//
//    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
