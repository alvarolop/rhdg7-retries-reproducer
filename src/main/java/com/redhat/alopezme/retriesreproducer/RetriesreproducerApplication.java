package com.redhat.alopezme.retriesreproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RetriesreproducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetriesreproducerApplication.class, args);
	}

}
