package pl.jacobit.kafkachat.config;

public final class KafkaConstants {
    private KafkaConstants() {} // prevent instantiation

    public static final String TOPIC_CHAT_MESSAGES = "chat-messages";
    public static final String GROUP_ID_CHAT = "chat-group";
}
