package dev.valente.fullstackapp.controller;

import org.springframework.web.socket.WebSocketSession;

public record SessionInfo(String origin, WebSocketSession webSocketSession) {
}
