package com.service.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.service.course"
        }

)
@EnableEurekaClient
public class CourseApplication {
    public static void main(String[] args) {

            SpringApplication.run(CourseApplication.class ,args);

    }
}