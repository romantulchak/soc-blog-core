package com.socblog.config;

import com.socblog.utils.UserLevelUp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.path.avatarFull}")
    private String fullUploadPath;

    @Value("${upload.path.post}")
    private String postsPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatars_min/**").addResourceLocations("file://" + uploadPath + "/" );
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/avatars_full/**").addResourceLocations("file://" + fullUploadPath +"/");
        registry.addResourceHandler("/posts_images/**").addResourceLocations("file://" + postsPath + "/");
    }


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



}
