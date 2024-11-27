package dev.valente.fullstackapp.singleton;

import org.springframework.web.socket.WebSocketSession;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class UserSessionSingleton {

    private static final ConcurrentHashMap<UUID, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    private UserSessionSingleton() {

    }

    public static ConcurrentHashMap<UUID, WebSocketSession> getInstance(){
        return userSessions;
    }
}
