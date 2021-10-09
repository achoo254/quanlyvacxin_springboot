package com.quanly.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.quanly.demo"})
public class QuanLyVacXinApplication {
    public static void main(String[] args) {

        SpringApplication.run(QuanLyVacXinApplication.class, args);
    }

}



