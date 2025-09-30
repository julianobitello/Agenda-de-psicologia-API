package com.api.psicoagenda.service;

import com.api.psicoagenda.entity.Psicologo;
import com.api.psicoagenda.repository.PsicologoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PsicologoService {

    private final PsicologoRepository psicologoRepository;

    public PsicologoService(PsicologoRepository psicologoRepository) {
        this.psicologoRepository = psicologoRepository;
    }

    public List<Psicologo> listarTodosPsicologos() {
        return psicologoRepository.findAll();
    }

    public Psicologo buscarPsicologoPorId(Long id) {
        return psicologoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psicologo não encontrado"));
    }

    public Psicologo criarPsicologo(Psicologo psicologo) {
        Optional<Psicologo> psicologoEncontrado = psicologoRepository.findByCpf(psicologo.getCpf())
                .or(() -> psicologoRepository.findByEmail(psicologo.getEmail()))
                .or(() -> psicologoRepository.findByCrp(psicologo.getCrp()));

        if(psicologoEncontrado.isPresent()){
            throw new RuntimeException("Já existe psicólogo com CPF, email ou CRP informado");
        }


        return psicologoRepository.save(psicologo);
    }

    public Psicologo atualizarPsicologo(Psicologo psicologo, Long id) {
        Psicologo psicologoAtualizado = psicologoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psicologo não encontrado"));

        psicologoAtualizado.setCpf(psicologo.getCpf());
        psicologoAtualizado.setCrp(psicologo.getCrp());
        psicologoAtualizado.setNome(psicologo.getNome());
        psicologoAtualizado.setEmail(psicologo.getEmail());
        psicologoAtualizado.setTelefone(psicologo.getTelefone());

        return psicologoRepository.save(psicologoAtualizado);
    }

    public Psicologo deletarPsicologo(Long id) {
        Psicologo psicologoEncontrado = psicologoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psicologo não encontrado"));

        psicologoRepository.delete(psicologoEncontrado);

        return psicologoEncontrado;
    }



}
