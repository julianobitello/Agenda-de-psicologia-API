package com.api.psicoagenda.service;

import com.api.psicoagenda.entity.Paciente;
import com.api.psicoagenda.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarTodosPacientes(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPacientesPorCPFouEmail(Paciente paciente) {
        return pacienteRepository.buscarPorCpf(paciente.getCpf())
                .or(() -> pacienteRepository.buscarPorEmail(paciente.getEmail()));
    }

    public Paciente criarPaciente(Paciente paciente) {
        Optional<Paciente> existePaciente = buscarPacientesPorCPFouEmail(paciente);

        if(existePaciente.isPresent()) {
            throw new RuntimeException("Já existe paciente com esse CPF ou Email");
        }

        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente atualizarPaciente(Paciente paciente, Long id) {
        Paciente pacienteExistente = buscarPacientePorId(id)
                .orElseThrow(() -> new RuntimeException("Não existe paciente com esse ID"));


        pacienteExistente.setNome(paciente.getNome());
        pacienteExistente.setEmail(paciente.getEmail());
        pacienteExistente.setCpf(paciente.getCpf());
        pacienteExistente.setTelefone(paciente.getTelefone());

        return pacienteRepository.save(pacienteExistente);
    }

    public Paciente deletarPaciente(Long id) {
        Paciente pacienteExistente = buscarPacientePorId(id)
                .orElseThrow(() -> new RuntimeException("Não existe paciente com esse ID"));

        pacienteRepository.delete(pacienteExistente);

        return pacienteExistente;
    }

}
