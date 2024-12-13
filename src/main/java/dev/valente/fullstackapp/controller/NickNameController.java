package dev.valente.fullstackapp.controller;

import dev.valente.fullstackapp.model.NickNameDTO;
import dev.valente.fullstackapp.service.NickNameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/register")
public class NickNameController {

    NickNameService nickNameService;

    public NickNameController(NickNameService nickNameService) {
        this.nickNameService = nickNameService;
    }

    @PostMapping
    public ResponseEntity<Object> registerNickName(@RequestBody NickNameDTO nickNameDTO) {
        nickNameService.saveNickName(nickNameDTO.nickName());
        return ResponseEntity.ok("Successfully registered!");
    }
}
