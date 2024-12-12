package dev.valente.fullstackapp.model;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

public class SessionInfoC {

    private String sessionId;

    private ChatInput chatInput;

    private WebSocketSession session;

    public SessionInfoC(String sessionId, WebSocketSession session) {
        this.sessionId = sessionId;
        this.session = session;
    }

    public ChatInput getChatInput() {
        return chatInput;
    }

    public void setChatInput(ChatInput chatInput) {
        this.chatInput = chatInput;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }
}
