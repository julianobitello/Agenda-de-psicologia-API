package com.api.psicoagenda.controller;

import com.api.psicoagenda.entity.Psicologo;
import com.api.psicoagenda.service.PsicologoService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/psicologo")
public class PsicologoController {

    private final PsicologoService psicologoService;

    public PsicologoController(PsicologoService psicologoService) {
        this.psicologoService = psicologoService;
    }

    @GetMapping
    public ResponseEntity<List<Psicologo>> listarTodosPsicologos() {
        return ResponseEntity.ok(psicologoService.listarTodosPsicologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Psicologo> buscarPsicologoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(psicologoService.buscarPsicologoPorId(id));
    }

    @PermitAll
    @PostMapping
    public ResponseEntity<Psicologo> criarPsicologo(@RequestBody Psicologo psicologo) {
        Psicologo psicologoCriado = psicologoService.criarPsicologo(psicologo);
        return ResponseEntity.status(HttpStatus.CREATED).body(psicologoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Psicologo> atualizarPsicologo(@RequestBody Psicologo psicologo, @PathVariable Long id) {
        return ResponseEntity.ok(psicologoService.atualizarPsicologo(psicologo, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Psicologo> deletarPsicologo(@PathVariable Long id) {
        return ResponseEntity.ok(psicologoService.deletarPsicologo(id));
    }




}
