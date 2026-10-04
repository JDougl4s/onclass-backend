package com.onclass.api.controllers;

import com.onclass.api.domain.Presenca;
import com.onclass.api.domain.SessaoAula;
import com.onclass.api.domain.Usuario;
import com.onclass.api.domain.PerfilUsuario;
import com.onclass.api.dtos.PresencaRequestDTO;
import com.onclass.api.repositories.PresencaRepository;
import com.onclass.api.repositories.SessaoAulaRepository;
import com.onclass.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/presencas")
public class PresencaController {

    @Autowired
    private PresencaRepository presencaRepository;

    @Autowired
    private SessaoAulaRepository sessaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registrar")
    public ResponseEntity<Object> registrar(@RequestBody PresencaRequestDTO data) {
        
        // 1. Busca a Sessão de Aula e verifica se ela ainda está ATIVA
        Optional<SessaoAula> sessaoOpt = sessaoRepository.findById(data.sessaoAulaId());
        
        if (sessaoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro: Sessão de aula não encontrada.");
        }
        if (!sessaoOpt.get().getStatusAtiva()) {
            return ResponseEntity.badRequest().body("Erro: Tempo esgotado! Esta sessão de aula já foi encerrada pelo professor.");
        }

        // 2. Busca o Aluno
        Optional<Usuario> alunoOpt = usuarioRepository.findById(data.alunoId());
        if (alunoOpt.isEmpty() || alunoOpt.get().getPerfil() != PerfilUsuario.ALUNO) {
            return ResponseEntity.badRequest().body("Erro: Aluno não encontrado ou perfil inválido.");
        }

        // 3. Monta a Presença
        Presenca novaPresenca = new Presenca();
        novaPresenca.setSessaoAula(sessaoOpt.get());
        novaPresenca.setAluno(alunoOpt.get());

        // 4. Salva no banco (com a trava de segurança contra presença dupla)
        try {
            presencaRepository.save(novaPresenca);
            return ResponseEntity.ok(novaPresenca);
        } catch (Exception e) {
            // O PostgreSQL vai barrar graças ao nosso @UniqueConstraint
            return ResponseEntity.badRequest().body("Erro: Você já registrou presença nesta aula!");
        }
    }
}