package ru.itis.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Objects;

@Configuration
@EnableWebMvc
@ComponentScan({"ru.itis.blog"})
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setCache(true);
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/ftl/templates/");
        return freeMarkerConfigurer;
    }

    @Bean
    public freemarker.template.Configuration getFreeMarkerConfiguration(FreeMarkerConfigurationFactoryBean configurationFactoryBean) {
        return configurationFactoryBean.getObject();
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfigurationFarctoryBean() {
        FreeMarkerConfigurationFactoryBean freeMarkerFactoryBean = new FreeMarkerConfigurationFactoryBean();
        freeMarkerFactoryBean.setTemplateLoaderPaths("/WEB-INF/ftl");
        freeMarkerFactoryBean.setPreferFileSystemAccess(true);
        freeMarkerFactoryBean.setDefaultEncoding("UTF-8");
        return freeMarkerFactoryBean;
    }

}