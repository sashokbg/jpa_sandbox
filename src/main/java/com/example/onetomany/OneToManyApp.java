package com.example.onetomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.*")
public class OneToManyApp {

	public static void main(String[] args) {
		SpringApplication.run(OneToManyApp.class, args);
	}

}
