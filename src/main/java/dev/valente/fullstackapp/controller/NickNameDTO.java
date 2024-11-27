package dev.valente.fullstackapp.controller;

import org.springframework.web.socket.WebSocketSession;

public record NickNameDTO(String origin, WebSocketSession webSocketSession) {
}
