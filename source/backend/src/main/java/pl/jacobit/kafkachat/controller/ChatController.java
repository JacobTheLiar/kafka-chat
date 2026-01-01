package pl.jacobit.kafkachat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import pl.jacobit.kafkachat.dto.SentMessageRequest;
import pl.jacobit.kafkachat.model.ChatMessage;
import pl.jacobit.kafkachat.service.KafkaProducerService;

import java.time.Instant;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final KafkaProducerService kafkaProducerService;


    @MessageMapping("/chat.send")
    public void sendMessage(SentMessageRequest request) {
        kafkaProducerService.sendMessage(
                new ChatMessage(request.roomName(), request.username(), request.message(), Instant.now())
        );
    }
}
