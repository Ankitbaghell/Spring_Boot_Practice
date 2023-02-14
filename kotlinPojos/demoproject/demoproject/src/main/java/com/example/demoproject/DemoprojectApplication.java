package com.example.demoproject;

import com.example.demoproject.model.entity.ProductEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class DemoprojectApplication {
	public static void main(String[] args) {

		ProductEntity product = new ProductEntity();
		System.out.println("product details : " + product);
		SpringApplication.run(DemoprojectApplication.class, args);
	}

}
