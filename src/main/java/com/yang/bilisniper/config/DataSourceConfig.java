package com.yang.bilisniper.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.yang.bilisniper.algorithm.ModuloShardingTableAlgorithm;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Configuration
public class DataSourceConfig {

    private static DataSource createDataSource(final String dbsName) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/bsniper?" +
                "autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false";
        dataSource.setUrl(url);
        dataSource.setUsername("yangchunqi");
        dataSource.setPassword("langshaoup916");
        return dataSource;
    }

    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        Map<String, DataSource> dbsMap = new HashMap<>();
        dbsMap.put("bsniper", createDataSource("bsniper"));

        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(getMemberTableRule());
        shardingRuleConfiguration.getBindingTableGroups().add("member");
        shardingRuleConfiguration.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("mid",
                ModuloShardingTableAlgorithm.class.getName()));

        return ShardingDataSourceFactory.createDataSource(dbsMap, shardingRuleConfiguration, new HashMap<>(), new Properties());
    }

    private TableRuleConfiguration getMemberTableRule() {
        TableRuleConfiguration memberTableRule = new TableRuleConfiguration();
        memberTableRule.setLogicTable("member");
        memberTableRule.setActualDataNodes("bsniper.member_${0..1}");
        memberTableRule.setKeyGeneratorColumnName("mid");

        return memberTableRule;
    }
}
