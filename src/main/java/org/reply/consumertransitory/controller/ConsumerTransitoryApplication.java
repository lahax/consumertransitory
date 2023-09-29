package org.reply.consumertransitory.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties
public class ConsumerTransitoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerTransitoryApplication.class, args);
	}

}
