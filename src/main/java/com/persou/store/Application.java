package com.persou.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("🚀 Kafka iniciada!");
        System.out.println("📝 AKHQ: http://localhost:8080");
    }

}
