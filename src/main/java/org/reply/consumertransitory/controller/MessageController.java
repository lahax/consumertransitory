package org.reply.consumertransitory.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reply.consumertransitory.KafkaConsumerService;
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

    @DeleteMapping("/deleteMessageList")
    public void deleteMessageList() throws InterruptedException {
        try {
            kafkaConsumerService.clearMessageList();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
