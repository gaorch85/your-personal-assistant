package com.gaorch.demo02;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.gaorch.demo02.mapper")
@SpringBootApplication
public class Demo02Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo02Application.class, args);
    }

    //调用 Flask API 所增加的功能
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
