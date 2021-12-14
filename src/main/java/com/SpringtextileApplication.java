package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.service.UserService;

@SpringBootApplication
public class SpringtextileApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx= SpringApplication.run(SpringtextileApplication.class, args);
		UserService service=ctx.getBean("us",UserService.class);
		//service.addorder(1);
		//service.addaddress("Vaish", "Gugai", "Salem", "TN", 950097725);
		//service.service();
		//service.getAllProduct();
		//service.displaycart(1);
		//service.sendemail(1);
	}
}