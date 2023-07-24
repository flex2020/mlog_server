package com.web.mlog_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${path.connect}")
    private String connectPath;
    @Value("${path.window}")
    private String windowPath;
    @Value("${path.ubuntu}")
    private String ubuntuPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name").toLowerCase();
        String resourcePath = (os.contains("win")) ? windowPath : ubuntuPath;

        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }
}