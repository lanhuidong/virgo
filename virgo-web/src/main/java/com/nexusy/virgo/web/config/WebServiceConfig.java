package com.nexusy.virgo.web.config;

import com.nexusy.virgo.data.config.DataSourceConfig;
import com.nexusy.virgo.data.config.MybatisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author lan
 * @since 2013-11-16
 */
@Configuration
@Import({DataSourceConfig.class, MybatisConfig.class})
@ImportResource("/WEB-INF/applicationContext-security.xml")
public class WebServiceConfig {
}
