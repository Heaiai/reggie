package com.heaiai.reggie.config;

import com.heaiai.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

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

    /***
     * @Description: 扩展mvc框架的消息转换区IAEA
     * @Author:Heaiai
     * @Create:2023/3/5 22:17
     */
   @Override
   protected void extendMessageConverters(List<HttpMessageConverter<?>> converters){
       log.info("扩展消息转换器...");
       //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用jackson将java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到Mvc框架的转换器集合中
       //放的下标越靠前越会被优先使用到
        converters.add(0,messageConverter);
   }
}
