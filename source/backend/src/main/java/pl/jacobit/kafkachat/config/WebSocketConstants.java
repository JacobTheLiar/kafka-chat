package pl.jacobit.kafkachat.config;

public final class WebSocketConstants {
    private WebSocketConstants() {} // prevent instantiation

    public static final String DESTINATION_PREFIX_TOPIC_ROOM = "/topic/room/";
    public static final String MAPPING_CHAT_SEND = "/chat.send";
}
