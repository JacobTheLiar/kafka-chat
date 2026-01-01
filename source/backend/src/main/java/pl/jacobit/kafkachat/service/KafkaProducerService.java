package pl.jacobit.kafkachat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.jacobit.kafkachat.config.KafkaConstants;
import pl.jacobit.kafkachat.model.ChatMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public void sendMessage(ChatMessage message) {
        log.info("Sending message to Kafka - Room: {}, User: {}, Message: {}", message.roomName(), message.username(), message.message());
        kafkaTemplate.send(KafkaConstants.TOPIC_CHAT_MESSAGES, message.roomName(), message);
    }
}
