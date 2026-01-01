package pl.jacobit.kafkachat.model;

import java.time.Instant;

public record ChatMessage(
        String roomName,
        String username,
        String message,
        Instant timestamp
) {
}
