package com.persistence.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class JavaPersistenceApplication extends SpringBootServletInitializer {

    private static final Class<JavaPersistenceApplication> applicationClass = JavaPersistenceApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(JavaPersistenceApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}