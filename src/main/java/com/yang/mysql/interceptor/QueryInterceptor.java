package com.yang.mysql.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({
        @Signature(
        method = "query",
        type = Executor.class,
        args ={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
        ),
        @Signature(
                method = "query",
                type = Executor.class,
          args ={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
}
)
public class QueryInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        long startTime = System.currentTimeMillis();
        System.out.println("拦截器执行开始，SQL执行");
        Object proceed = invocation.proceed();
        System.out.println("SQL执行完成，共耗时："+(System.currentTimeMillis()-startTime)+"ms");
        return proceed;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
