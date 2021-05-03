package com.tekion.dynamodblocaldemo.configuration.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
        basePackages = {"com.tekion"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.tekion\\..*launcher\\..*")
        })
@SpringBootApplication
public class DynamodblocaldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamodblocaldemoApplication.class, args);
    }

}
