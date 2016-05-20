package com.nexusy.virgo.data.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @author lan
 * @since 2013-11-07
 */
@Configuration
@Import(DataConfig.class)
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(env.getProperty("jdbc.datasource.classname"));
        config.setUsername(env.getProperty("jdbc.username"));
        config.setPassword(env.getProperty("jdbc.password"));
        config.addDataSourceProperty("url", env.getProperty("jdbc.url"));

        return new HikariDataSource(config);
    }
}
