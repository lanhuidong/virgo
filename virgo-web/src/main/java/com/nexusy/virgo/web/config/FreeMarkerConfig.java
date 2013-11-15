package com.nexusy.virgo.web.config;

import freemarker.template.utility.XmlEscape;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 配置FreeMarker
 *
 * @author lan
 * @since 2013-10-26
 */
@Configuration
public class FreeMarkerConfig {

    @Bean
    FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();

        freeMarkerConfigurer.setTemplateLoaderPath("/");

        Properties settings = new Properties();
        settings.put("locale", "zh_CN"); //本地化设置
        settings.put("datetime_format", "yyyy-MM-dd"); //日期格式设置
        settings.put("number_format", "0.##");  //数字格式设置
        settings.put("url_escaping_charset", "UTF-8");
        settings.put("output_encoding", "UTF-8");
        settings.put("auto_import", "/WEB-INF/ftl/macro/common.ftl as c");
        freeMarkerConfigurer.setFreemarkerSettings(settings);

        Map<String, Object> variables = new HashMap<>();
        variables.put("xml_escape", new XmlEscape());
        freeMarkerConfigurer.setFreemarkerVariables(variables);

        freeMarkerConfigurer.setDefaultEncoding("UTF-8");

        return freeMarkerConfigurer;
    }

    @Bean
    FreeMarkerViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();

        viewResolver.setPrefix("/WEB-INF/ftl");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setExposeSpringMacroHelpers(true);

        return viewResolver;
    }
}
