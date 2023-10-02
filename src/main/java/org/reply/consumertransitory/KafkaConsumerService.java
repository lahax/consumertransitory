package org.reply.consumertransitory;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableKafka
@EnableConfigurationProperties
public class KafkaConsumerService {

    public static List<Message> messageList = new ArrayList<>();

    @KafkaListener(topics = {"Event", "Diagnostics", "digic_event", "digic_diagnostics"}, groupId="consumer-transitory")
    public void listen(ConsumerRecord<String, String> record){
        String topic = record.topic();
        String payload = record.value();
        String piattaforma = "Transitoria";

        try {
            Message message = new Message(piattaforma, topic, payload);
            messageList.add(message);
            System.out.println(message.payload.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Message> getMessageList() {
        return messageList;
    }

    public void clearMessageList() throws InterruptedException {
        messageList.clear(); //svuotare lista dopo l'invio al Core
    }
}