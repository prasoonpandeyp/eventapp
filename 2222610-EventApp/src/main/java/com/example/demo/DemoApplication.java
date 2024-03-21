package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.config.JwtTokenGenerator;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	//create Bean for JwtTokenGenerator
	@Bean
	public JwtTokenGenerator jwtTokenGenerator() {
		return new JwtTokenGenerator();
	}
}
