package com.mavi.restrailwaysecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.mavi.restrailwaysecurity.repository",
        repositoryBaseClass = SimpleJpaRepository.class)
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.mavi.restrailwaysecurity", "com.mavi.restrailwaysecurity.config"})
public class RestRailwaySecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestRailwaySecurityApplication.class, args);


    }
}
