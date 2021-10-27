package com.yang.mysql.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * @author daniel
 * @date 2021/5/2
 *
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private Product0Config database0Config;

    @Autowired
    private Product1Config database1Config;

    @Autowired
    private DatabaseShardingAlgorithm databaseShardingAlgorithm;

    @Autowired
    private TableShardingAlgorithm tableShardingAlgorithm;

    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        //分库设置
        Map<String, DataSource> dataSourceMap = Maps.newHashMapWithExpectedSize(2);
        //添加两个数据库database0和database1
        dataSourceMap.put(database0Config.getDatabaseName(), database0Config.createDataSource());
        dataSourceMap.put(database1Config.getDatabaseName(), database1Config.createDataSource());
        //设置默认数据库
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, database0Config.getDatabaseName());

        //分表设置，大致思想就是将查询虚拟表Goods根据一定规则映射到真实表中去
        TableRule orderTableRule = TableRule.builder("product")
                .actualTables(Arrays.asList("product_0", "product_1", "product_2", "product_3", "product_4", "product_5", "product_6",
                        "product_7", "product_8", "product_9", "product_10", "product_11", "product_12", "product_13", "product_14",
                        "product_15", "product_16", "product_17", "product_18", "product_19", "product_20", "product_21", "product_22",
                        "product_23", "product_24", "product_25", "product_26", "product_27", "product_28", "product_29", "product_30",
                        "product_31", "product_32", "product_33", "product_34", "product_35", "product_36", "product_37", "product_38",
                        "product_39", "product_40", "product_41", "product_42", "product_43", "product_44", "product_45", "product_46",
                        "product_47", "product_48", "product_49", "product_50", "product_51", "product_52", "product_53", "product_54",
                        "product_55", "product_56", "product_57", "product_58", "product_59", "product_60", "product_61", "product_62",
                        "product_63"))
                .dataSourceRule(dataSourceRule)
                .build();

        //分库分表策略
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Collections.singletonList(orderTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("store_id", databaseShardingAlgorithm))
                .tableShardingStrategy(new TableShardingStrategy("store_id", tableShardingAlgorithm)).build();
        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }


    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }

}
