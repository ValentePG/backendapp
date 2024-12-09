package dev.valente.fullstackapp.model;

import org.springframework.web.socket.WebSocketSession;

public record SessionInfo(String origin, WebSocketSession webSocketSession) {
}
