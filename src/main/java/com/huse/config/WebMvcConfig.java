package com.huse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //设置虚拟路径映射,这样SpringBoot就可以访问本地资源了
        //草泥马必须得这个狗比格式,恶几把心
//        registry.addResourceHandler("/headpic/**").addResourceLocations("file:E:/HUSEFile/headportrait/");
        registry.addResourceHandler("/headpic/**").addResourceLocations("file:/root/usr/local/HUSEFile/headportrait/");
//        registry.addResourceHandler("/doc/**").addResourceLocations("file:E:/HUSEFile/doc/");
        registry.addResourceHandler("/doc/**").addResourceLocations("file:/root/usr/local/HUSEFile/doc/");
//        registry.addResourceHandler("/sys_file/**").addResourceLocations("file:E:/HUSEFile/sys_file/");
        registry.addResourceHandler("/sys_file/**").addResourceLocations("file:/root/usr/local/HUSEFile/sys_file/");

    }
}
