package com.example.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

//Todo: Factory implemented
//Todo: Observer implemented
//Todo: By default all bean classes are singleton
public class WebShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }
}
