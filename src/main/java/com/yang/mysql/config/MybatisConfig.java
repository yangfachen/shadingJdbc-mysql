package com.yang.mysql.config;

import com.yang.mysql.interceptor.QueryInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Configuration
public class MybatisConfig {

    @Resource
    private List<SqlSessionFactory> sessionFactoryList;

    @PostConstruct
    public void addInterceptor(){
        QueryInterceptor queryInterceptor = new QueryInterceptor();
        sessionFactoryList.forEach(sqlSessionFactory -> sqlSessionFactory.getConfiguration().addInterceptor(queryInterceptor));
    }
}
