package dev.valente.fullstackapp.controller;


import dev.valente.fullstackapp.model.NickName;
import dev.valente.fullstackapp.repository.NickNameRepository;
import dev.valente.fullstackapp.singleton.UserSessionSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("api/v1/create")
public class NickNameController {

    private final ConcurrentHashMap<UUID, WebSocketSession> userSessions = UserSessionSingleton.getInstance();

    @Autowired
    private NickNameRepository nickNameRepository;

    @PostMapping
    public ResponseEntity<String> createNickName(@RequestBody NickNameDTO nickNameDTO){

        var novoUsuario = new NickName(nickNameDTO.nickName());

        nickNameRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
