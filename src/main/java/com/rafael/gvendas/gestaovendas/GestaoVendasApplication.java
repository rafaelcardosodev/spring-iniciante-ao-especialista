package com.rafael.gvendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.rafael.gvendas.gestaovendas.entities"})
@EnableJpaRepositories(basePackages = {"com.rafael.gvendas.gestaovendas.repository"})
@ComponentScan(basePackages = {"com.rafael.gvendas.gestaovendas.controllers", "com.rafael.gvendas.gestaovendas.services"})
@SpringBootApplication
public class GestaoVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVendasApplication.class, args);
	}

}
