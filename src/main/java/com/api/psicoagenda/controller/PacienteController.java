package com.api.psicoagenda.controller;

import com.api.psicoagenda.entity.Paciente;
import com.api.psicoagenda.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodosPacientes() {
        List<Paciente> pacientes = pacienteService.listarTodosPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<Paciente> criarNovoPaciente(@RequestBody Paciente paciente) {
       Paciente pacienteCriado = pacienteService.criarPaciente(paciente);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) {
        Paciente pacienteEncontrado = pacienteService.buscarPacientePorId(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        return ResponseEntity.ok(pacienteEncontrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
         Paciente pacienteAtualizado = pacienteService.atualizarPaciente(paciente, id);

         return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> deletarPaciente(@PathVariable Long id) {
        Paciente pacienteDeletado = pacienteService.deletarPaciente(id);
        return ResponseEntity.ok(pacienteDeletado);
    }
}
