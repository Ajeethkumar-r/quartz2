package com.quartz2.q2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({"com.delivery.request"})
public class Q2Application {
	public static void main(String[] args) {
		SpringApplication.run(Q2Application.class, args);
	}
}
