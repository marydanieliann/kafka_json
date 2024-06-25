package org.example.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.kafka.model.KafkaMessage;
import org.example.kafka.repository.KafkaMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumerService {
    private final KafkaMessageRepository kafkaMessageRepository;

    @Autowired
    public KafkaConsumerService(KafkaMessageRepository kafkaMessageRepository) {
        this.kafkaMessageRepository = kafkaMessageRepository;
    }

    @KafkaListener(topics = "devnation", groupId = "devnation-spring")
    public void consume(ConsumerRecord<String, String> record) {
        String jsonMessage = record.value();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            KafkaMessage kafkaMessage = objectMapper.readValue(jsonMessage, KafkaMessage.class);
            kafkaMessageRepository.save(kafkaMessage);
            System.out.println("Message saved: " + kafkaMessage.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
