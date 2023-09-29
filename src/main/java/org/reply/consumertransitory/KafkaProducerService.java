package org.reply.consumertransitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendList(List<ProducerMessage> messageList) {
        for(ProducerMessage message : messageList) {
        kafkaTemplate.send(message.getTopic(), message.getValue());
        }
    }

    public void send(ProducerMessage message) {
            kafkaTemplate.send(message.getTopic(), message.getValue());
        }
    }

