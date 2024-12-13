package dev.valente.fullstackapp.service;

import dev.valente.fullstackapp.model.NickName;
import dev.valente.fullstackapp.model.SessionInfoC;
import dev.valente.fullstackapp.repository.NickNameRepository;
import dev.valente.fullstackapp.singleton.UserSessionSingleton;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NickNameService {

    private final ConcurrentHashMap<UUID, SessionInfoC> userSessions = UserSessionSingleton.getInstance();

    private final NickNameRepository nickNameRepository;

    public NickNameService(NickNameRepository nickNameRepository) {
        this.nickNameRepository = nickNameRepository;
    }

    public void saveNickName(String nickName) {
        NickName nickName1 = new NickName(nickName);
        nickNameRepository.save(nickName1);
    }
}
