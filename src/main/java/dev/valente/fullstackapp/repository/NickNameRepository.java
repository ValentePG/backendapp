package dev.valente.fullstackapp.repository;

import dev.valente.fullstackapp.model.NickName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NickNameRepository extends JpaRepository<NickName, UUID> {

}
