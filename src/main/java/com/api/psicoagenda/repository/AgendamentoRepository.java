package com.api.psicoagenda.repository;

import com.api.psicoagenda.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByPacienteId(Long pacienteId);
    List<Agendamento> findByPsicologoId(Long psicologoId);
    List<Agendamento> findByPsicologoIdAndDataHora(Long psicologoId, LocalDateTime dataHora);
    List<Agendamento> findByPacienteIdAndDataHora(Long pacienteId, LocalDateTime dataHora);

}
