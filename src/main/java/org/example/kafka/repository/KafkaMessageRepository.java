package org.example.kafka.repository;

import org.example.kafka.model.KafkaMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaMessageRepository extends JpaRepository<KafkaMessage, Long> {
}
