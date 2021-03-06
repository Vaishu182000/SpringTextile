package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class SpringtextileApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx= SpringApplication.run(SpringtextileApplication.class, args);
	}
}