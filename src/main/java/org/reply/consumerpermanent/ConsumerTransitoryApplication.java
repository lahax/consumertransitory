package org.reply.consumerpermanent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ConsumerTransitoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerTransitoryApplication.class, args);
	}

}
