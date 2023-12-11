package org.reply.consumertransitory;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableConfigurationProperties
public class KafkaConsumerService {

    public static List<Message> messageList = new ArrayList<>();
    private boolean isListening = false;

    @KafkaListener(topics = {"event", "diagnostics", "digic_event", "digic_diagnostics", "digic_unsolicited"}, groupId = "consumer-transitory")
    public void listen(ConsumerRecord<String, String> record){
        if(isListening){
            String topic = record.topic();
            String payload = record.value();
            String piattaforma = "Transitoria";
            String fixedPayload = payload.replace("${metric.value}", "\"${metric.value}\"");

            try {
                Message message = new Message(piattaforma, topic, fixedPayload);
                messageList.add(message);
                System.out.println(message.payload.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public List<Message> getMessageList() {
        return messageList;
    }

    public void clearMessageList(){
        messageList.clear(); //svuotare lista dopo l'invio al Core
    }
    public void startListening() {
        clearMessageList();
        System.out.println("START CONSUMING");
        isListening = true;
    }
    public void stopListening() {
        System.out.println("STOP CONSUMING");
        isListening = false;
        clearMessageList();
    }
    public boolean getStatus(){ return isListening;}
}