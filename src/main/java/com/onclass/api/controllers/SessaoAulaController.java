package com.onclass.api.controllers;

import com.onclass.api.domain.Disciplina;
import com.onclass.api.domain.SessaoAula;
import com.onclass.api.dtos.SessaoAulaRequestDTO;
import com.onclass.api.repositories.DisciplinaRepository;
import com.onclass.api.repositories.SessaoAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sessoes")
public class SessaoAulaController {

    @Autowired
    private SessaoAulaRepository sessaoAulaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @PostMapping("/abrir")
    public ResponseEntity<Object> abrirSessao(@RequestBody SessaoAulaRequestDTO data) {
        
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(data.disciplinaId());
        
        if (disciplinaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro: Disciplina não encontrada.");
        }

        SessaoAula novaSessao = new SessaoAula();
        novaSessao.setDisciplina(disciplinaOpt.get());
        // dataHoraAbertura e statusAtiva já são preenchidos automaticamente pela Entidade

        sessaoAulaRepository.save(novaSessao);

        return ResponseEntity.ok(novaSessao);
    }
}