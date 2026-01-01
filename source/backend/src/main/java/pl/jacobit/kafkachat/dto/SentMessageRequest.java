package pl.jacobit.kafkachat.dto;

public record SentMessageRequest(
        String roomName,
        String username,
        String message
) {
}
