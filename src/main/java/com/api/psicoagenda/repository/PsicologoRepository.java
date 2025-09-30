package com.api.psicoagenda.repository;

import com.api.psicoagenda.entity.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PsicologoRepository extends JpaRepository<Psicologo, Long> {
    Optional<Psicologo> findByCpf(String cpf);
    Optional<Psicologo> findByEmail(String email);
    Optional<Psicologo> findByCrp(String crp);
}
