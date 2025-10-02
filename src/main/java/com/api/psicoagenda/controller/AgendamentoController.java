package com.api.psicoagenda.controller;

import com.api.psicoagenda.entity.Agendamento;
import com.api.psicoagenda.service.AgendamentoService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarTodos() {
        return ResponseEntity.ok(agendamentoService.listarTodosAgendamentos());
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Agendamento>> buscarPaciente(@PathVariable Long id) {
        List<Agendamento> pacienteEncontrado = agendamentoService.buscarPorPaciente(id);

        return ResponseEntity.ok(pacienteEncontrado);
    }

    @GetMapping("/psicologo/{id}")
    public ResponseEntity<List<Agendamento>> buscarPsicologo(@PathVariable Long id) {
        List<Agendamento> psicologoEncontrado = agendamentoService.buscarPorPsicologo(id);

        return ResponseEntity.ok(psicologoEncontrado);
    }

    @PostMapping
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody Agendamento agendamento) {
        Agendamento agendamentoCriado = agendamentoService.criarAgendamento(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoCriado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
        agendamentoService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(
            @PathVariable Long id,
            @RequestBody Agendamento agendamento) {

        Agendamento agendamentoAtualizado = agendamentoService.atualizarAgendamento(id, agendamento);
        return ResponseEntity.ok(agendamentoAtualizado);
    }


}
