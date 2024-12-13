package dev.valente.fullstackapp.repository;

import dev.valente.fullstackapp.model.NickName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NickNameRepository extends JpaRepository<NickName, Integer> {
}
