package org.reply.consumertransitory.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reply.consumertransitory.KafkaConsumerService;
import org.reply.consumertransitory.KafkaProducerService;
import org.reply.consumertransitory.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kafkaConsumer")
public class MessageController {

    private static final Logger log = LogManager.getLogger();
    @Autowired
    private KafkaConsumerService kafkaConsumerService;
    @Autowired
    private KafkaProducerService kafkaProducerService;


    @GetMapping("/getMessage")
    public ResponseEntity<List<Message>> getMessage() throws InterruptedException {
        try {
            List<Message> messageListCopy = new ArrayList<>(kafkaConsumerService.getMessageList());
            return ResponseEntity.ok(messageListCopy);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/postOnEvent")
    public ResponseEntity<String> sendEvent(@RequestBody String message) {
        kafkaProducerService.sendEvent(message);
        return ResponseEntity.ok("Message sent successfully on Event topic");
    }
    @PostMapping("/postOnDiagnostics")
    public ResponseEntity<String> sendDiagnostics(@RequestBody String message) {
        kafkaProducerService.sendDiagnostics(message);
        return ResponseEntity.ok("Message sent successfully on Diagnostics topic");
    }
    @PostMapping("/postOnDigicEvent")
    public ResponseEntity<String> sendDigicEvent(@RequestBody String message) {
        kafkaProducerService.sendDigicEvent(message);
        return ResponseEntity.ok("Message sent successfully on Digic Event topic");
    }
    @PostMapping("/postOnDigicDiagnostics")
    public ResponseEntity<String> sendDigicDiagnostics(@RequestBody String message) {
        kafkaProducerService.sendDigicDiagnostics(message);
        return ResponseEntity.ok("Message sent successfully on Digic Diagnostics topic");
    }
    @PostMapping("/postOnUnsolicited")
    public ResponseEntity<String> sendUnsolicited(@RequestBody String message) {
        kafkaProducerService.sendUnsolicited(message);
        return ResponseEntity.ok("Message sent successfully on Digic Unsolicited topic");
    }

    @DeleteMapping("/deleteMessageList")
    public void deleteMessageList() throws InterruptedException {
        try {
            kafkaConsumerService.clearMessageList();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        String response = "Transitory Consumer Status: OK - ";
        if(kafkaConsumerService.getStatus()) response += "Listening";
        else response += "Stopped";
        return ResponseEntity.ok(response);
    }

    @PostMapping("/start")
    public ResponseEntity<String> start() {
        kafkaConsumerService.startListening();
        return ResponseEntity.ok("Transitory Consumer Started");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stop() {
        kafkaConsumerService.stopListening();
        return ResponseEntity.ok("Transitory Consumer Stopped");
    }

}
