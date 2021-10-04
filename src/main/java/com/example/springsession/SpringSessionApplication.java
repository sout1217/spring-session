package com.example.springsession;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSessionApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SpringSessionApplication.class, args);
    }
}
