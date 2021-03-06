package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class,scanBasePackages={
		"com.example.*"})
public class YamlExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(YamlExampleApplication.class, args);
	}

}
