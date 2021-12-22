package com.example.c_p;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CPApplication {

	public static void main(String[] args) {
		SpringApplication.run(CPApplication.class, args);
		System.out.println("spring is running!");
	}

}
