package org.reply.consumertransitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendEvent(String message) {
        kafkaTemplate.send("Event", message);
    }
    public void sendDiagnostics(String message) {
        kafkaTemplate.send("Diagnostics", message);
    }
    public void sendDigicEvent(String message) {
        kafkaTemplate.send("digic_event", message);
    }
    public void sendDigicDiagnostics(String message) {
        kafkaTemplate.send("digic_diagnostics", message);
    }
}

