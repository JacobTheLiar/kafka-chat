package pl.jacobit.kafkachat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.jacobit.kafkachat.model.ChatMessage;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    public static final String TOPIC = "chat-messages";
    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public void sendMessage(ChatMessage message) {
        kafkaTemplate.send(TOPIC, message.roomName(), message);
    }
}
