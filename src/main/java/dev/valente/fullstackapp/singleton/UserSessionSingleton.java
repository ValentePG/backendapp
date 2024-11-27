package dev.valente.fullstackapp.singleton;

import dev.valente.fullstackapp.controller.NickNameDTO;
import org.springframework.web.socket.WebSocketSession;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class UserSessionSingleton {

    private static final ConcurrentHashMap<UUID, NickNameDTO> userSessions = new ConcurrentHashMap<>();

    private UserSessionSingleton() {

    }

    public static ConcurrentHashMap<UUID, NickNameDTO> getInstance(){
        return userSessions;
    }
}
