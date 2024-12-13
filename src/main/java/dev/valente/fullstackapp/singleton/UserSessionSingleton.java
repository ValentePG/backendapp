package dev.valente.fullstackapp.singleton;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import dev.valente.fullstackapp.model.SessionInfoC;

public final class UserSessionSingleton {

    private static final ConcurrentHashMap<UUID, SessionInfoC> userSessions = new ConcurrentHashMap<>();

    private UserSessionSingleton() {

    }

    public static ConcurrentHashMap<UUID, SessionInfoC> getInstance(){
        return userSessions;
    }
}
