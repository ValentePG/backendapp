package dev.valente.fullstackapp.model;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

public class SessionInfoC {

    private UUID sessionId;

    private ChatInput chatInput;

    private WebSocketSession session;

    public SessionInfoC(UUID sessionId, WebSocketSession session) {
        this.sessionId = sessionId;
        this.session = session;
    }

    public ChatInput getChatInput() {
        return chatInput;
    }

    public void setChatInput(ChatInput chatInput) {
        this.chatInput = chatInput;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }
}
