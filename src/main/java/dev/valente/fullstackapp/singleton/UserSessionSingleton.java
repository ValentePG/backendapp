package dev.valente.fullstackapp.singleton;

import dev.valente.fullstackapp.controller.SessionInfo;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class UserSessionSingleton {

    private static final ConcurrentHashMap<UUID, SessionInfo> userSessions = new ConcurrentHashMap<>();

    private UserSessionSingleton() {

    }

    public static ConcurrentHashMap<UUID, SessionInfo> getInstance(){
        return userSessions;
    }
}
