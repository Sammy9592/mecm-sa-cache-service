package com.sl.mecm.service.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sl.mecm.*")
@EnableAutoConfiguration(exclude = org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class)
public class CacheServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(CacheServiceApplication.class);

    public static void main(String[] args) {
        try {
            logger.debug("start cache service application debug");
            logger.info("start cache service application info");
            logger.error("start cache service application error");
            SpringApplication.run(CacheServiceApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}