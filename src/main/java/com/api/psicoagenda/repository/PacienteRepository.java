package com.api.psicoagenda.repository;

import com.api.psicoagenda.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> buscarPorCpf(String cpf);

    Optional<Paciente> buscarPorEmail(String email);
}
