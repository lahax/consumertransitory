package org.reply.consumerpermanent;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
@EnableConfigurationProperties
public class KafkaConsumer {

    @KafkaListener(topics = {"Event", "Diagnostics", "digic_event", "digic_diagnostics"}, groupId="json-consumer-transitory")
    public void readMessage(String message){
        System.out.println(message);
    }

}