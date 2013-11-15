package com.nexusy.virgo.data.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

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
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        try {
            dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        dataSource.setMinPoolSize(5);
        dataSource.setMaxPoolSize(10);
        dataSource.setAcquireIncrement(5);
        dataSource.setMaxIdleTime(1800);
        dataSource.setIdleConnectionTestPeriod(180);

        return dataSource;
    }
}
