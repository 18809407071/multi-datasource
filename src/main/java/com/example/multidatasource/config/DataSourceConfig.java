package com.example.multidatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Description TODO 数据源配置及jdbcTempt配置
 * @Author caoti
 * @Date 2020/3/13 15:44
 * @since Jdk 1.8
 **/
@Configuration
public class DataSourceConfig {

    /**
     * @description 第一个数据源 用于studb数据库
     * @author caoti
     */
    @Bean(name = "studbDataSource")
    @Qualifier("studbDataSource")
    @ConfigurationProperties(prefix="spring.datasource.data-studb")
    @Primary
    public DataSource primaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * @description 第二个数据源 用于test1数据库
     * @author caoti
     */
    @Bean(name = "test1DataSource")
    @Qualifier("test1DataSource")
    @ConfigurationProperties(prefix="spring.datasource.data-test1")
    public DataSource secondaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * @description 定义操作studb数据库的jdbcTemplate
     * @author caoti
     */
    @Bean(name = "studbJdbcTemplate")
    @Primary
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("studbDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * @description 定义操作test1数据库的jdbcTemplate
     * @author caoti
     */
    @Bean(name = "test1JdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("test1DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
