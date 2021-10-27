package com.yang.mysql.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Data
@ConfigurationProperties(prefix = "product0")
@Component
public class Product0Config {

    private String url;

    private String userName;

    private String password;

    private String driverClassName;

    private String databaseName;

    public DataSource createDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(getUserName());
        dataSource.setPassword(getPassword());
        dataSource.setUrl(getUrl());
        dataSource.setDriverClassName(getDriverClassName());

        return dataSource;
    }
}
