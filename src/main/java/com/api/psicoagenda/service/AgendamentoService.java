package com.api.psicoagenda.service;

import com.api.psicoagenda.entity.Agendamento;
import com.api.psicoagenda.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Agendamento> listarTodosAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public List<Agendamento> buscarPorPaciente(Long pacienteId) {
        return agendamentoRepository.findByPacienteId(pacienteId);
    }

    public List<Agendamento> buscarPorPsicologo(Long psicologoId) {
        return agendamentoRepository.findByPsicologoId(psicologoId);
    }

    public Agendamento criarAgendamento(Agendamento agendamento) {
        if (!agendamentoRepository.findByPsicologoIdAndDataHora(agendamento.getPsicologo().getId(), agendamento.getDataHora()).isEmpty()) {
            throw new RuntimeException("Psicólogo já possui agendamento nesse horário");
        }

        if (!agendamentoRepository.findByPacienteIdAndDataHora(agendamento.getPaciente().getId(), agendamento.getDataHora()).isEmpty()) {
            throw new RuntimeException("Paciente já possui agendamento nesse horário");
        }

        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamentoRepository.deleteById(id);
    }

    public Agendamento atualizarAgendamento(Long id, Agendamento agendamento) {
        Agendamento agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        agendamentoExistente.setDataHora(agendamento.getDataHora());
        agendamentoExistente.setObservacao(agendamento.getObservacao());
        agendamentoExistente.setStatus(agendamento.getStatus());
        agendamentoExistente.setPaciente(agendamento.getPaciente());
        agendamentoExistente.setPsicologo(agendamento.getPsicologo());

        return agendamentoRepository.save(agendamentoExistente);
    }


}
