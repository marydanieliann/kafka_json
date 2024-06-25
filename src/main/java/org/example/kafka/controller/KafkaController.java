package org.example.kafka.controller;

import org.example.kafka.model.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody KafkaMessage kafkaMessage) {
        try {
            kafkaTemplate.send("devnation", "{\"message\":\"" + kafkaMessage.getMessage() + "\"}");
            return "Message sent: " + kafkaMessage.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending message: " + e.getMessage();
        }
    }
}
