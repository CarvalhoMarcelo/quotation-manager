package com.inatel.quotationmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QuotationManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotationManagerApplication.class, args);
	}

}
