package com.heaiai.reggie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description mvc静态资源访问配置类
 * @author: Heaiai
 * @create: 2023-02-15 21:39:55
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * @Description:设置静态资源映射
     *  这里配置之后，前台项目就可以直接访问静态资源，否则只有在/resources/static或者/resources/public里面的静态资源才可以被访问
     * @Author:Heaiai
     * @Create:2023/2/15 21:44
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始静态资源映射....");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

}
