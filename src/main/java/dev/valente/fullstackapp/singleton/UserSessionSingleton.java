package dev.valente.fullstackapp.singleton;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import dev.valente.fullstackapp.model.SessionInfo;

public final class UserSessionSingleton {

    private static final ConcurrentHashMap<UUID, SessionInfo> userSessions = new ConcurrentHashMap<>();

    private UserSessionSingleton() {

    }

    public static ConcurrentHashMap<UUID, SessionInfo> getInstance(){
        return userSessions;
    }
}
