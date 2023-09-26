package org.reply.consumerpermanent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
@EnableConfigurationProperties
public class KafkaConsumerService {

    @KafkaListener(topics = {"Event", "Diagnostics", "digic_event", "digic_diagnostics"}, groupId="json-consumer-transitory")
    public void listen(ConsumerRecord<String, String> record){
        String topic = record.topic();
        String value = record.value();
        String groupId = "Transitoria";

        try {
            // Parse the JSON message using an ObjectMapper (Jackson, for example)
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(value);
            //String groupId = record.headers().lastHeader("groupId").toString();

            String posId = jsonNode.get("posId").asText();
            String useCase = jsonNode.get("useCase").asText();
            String scopeId = jsonNode.get("scopeId").asText();
            String clientId = jsonNode.get("clientId").asText();
            JsonNode payload = jsonNode.get("payload");
            long receivedOn = jsonNode.get("receivedOn").asLong();
            JsonNode channel = jsonNode.get("channel");
            String posIdb = jsonNode.get("posId_B").toString();
            String posIda = jsonNode.get("posId_A").toString();
            JsonNode posGeo = jsonNode.get("posGeo");
            String account = jsonNode.get("account").toString();

            System.out.println("From platform: + "+ groupId + " From topic: " + topic + " posId: " + posId + ", useCase: " + useCase + " scopeId: " + scopeId + " clientId: " + clientId + " payload: " + payload + " receivedOn: " + receivedOn + " channel: " + channel + " posId_B: " + posIdb + " posId_A: " + posIda + " posGeo: " + posGeo + " account: " + account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}