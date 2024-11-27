package dev.valente.fullstackapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class NickName {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nickName;

    public NickName(UUID id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public NickName(String nickName) {
        this.nickName = nickName;
    }

    public NickName() {
    }

    public UUID getId() {
        return id;
    }


    public String getNickName() {
        return nickName;
    }
}
