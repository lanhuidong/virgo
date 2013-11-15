package com.nexusy.virgo.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author lan
 * @since 2013-11-07
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.nexusy.virgo.data.service", "com.nexusy.virgo.data.dao"})
@PropertySource("classpath:jdbc.properties")
public class DataConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.nexusy.virgo.data.model");

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.query.substitutions", env.getProperty("hibernate.query.substitutions"));
        hibernateProperties.put("hibernate.cache.use_second_level_cache",
                env.getProperty("hibernate.cache.use_second_level_cache"));
        hibernateProperties.put("hibernate.cache.provider_class", env.getProperty("hibernate.cache.provider_class"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}
