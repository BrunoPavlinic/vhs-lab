package com.example.vhs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Slf4j
@EnableSwagger2

public class VhsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VhsApplication.class, args);
		log.info("Application started!");
	}
}
