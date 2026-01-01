package pl.jacobit.kafkachat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import pl.jacobit.kafkachat.config.KafkaConstants;
import pl.jacobit.kafkachat.model.ChatMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = KafkaConstants.TOPIC_CHAT_MESSAGES, groupId = KafkaConstants.GROUP_ID_CHAT)
    public void consumeMessage(ConsumerRecord<String, ChatMessage> consumedRecord) {
        var roomName = consumedRecord.key();
        var message = consumedRecord.value();

        log.info("Received from Kafka - Room: {}, User: {}, Message: {}", roomName, message.username(), message.message());

        messagingTemplate.convertAndSend("/topic/room/" + roomName, message);
    }
}
