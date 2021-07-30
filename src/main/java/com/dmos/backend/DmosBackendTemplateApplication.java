package com.dmos.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class DmosBackendTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmosBackendTemplateApplication.class, args);
    }

    //This is required for accessing the HttpServletRequest object in DomainUserDetailsService.loadUserByUsername which is a spring callback method in the spring security authentication flow.
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

}
