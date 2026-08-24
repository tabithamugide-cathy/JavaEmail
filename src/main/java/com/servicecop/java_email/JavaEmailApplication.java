package com.servicecop.java_email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaEmailApplication.class, args);
	}

}
