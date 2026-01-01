package pl.jacobit.kafkachat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import pl.jacobit.kafkachat.dto.SentMessageRequest;
import pl.jacobit.kafkachat.model.ChatMessage;
import pl.jacobit.kafkachat.service.KafkaProducerService;

import java.time.Instant;

import static pl.jacobit.kafkachat.config.WebSocketConstants.MAPPING_CHAT_SEND;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final KafkaProducerService kafkaProducerService;


    @MessageMapping(MAPPING_CHAT_SEND)
    public void sendMessage(SentMessageRequest request) {
        log.info("Got message to Kafka - Room: {}, User: {}, Message: {}", request.roomName(), request.username(), request.message());
        kafkaProducerService.sendMessage(
                new ChatMessage(request.roomName(), request.username(), request.message(), Instant.now())
        );
    }
}
