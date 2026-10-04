package com.onclass.api.controllers;

import com.onclass.api.domain.Disciplina;
import com.onclass.api.domain.Matricula;
import com.onclass.api.domain.Usuario;
import com.onclass.api.domain.PerfilUsuario;
import com.onclass.api.dtos.MatriculaRequestDTO;
import com.onclass.api.repositories.DisciplinaRepository;
import com.onclass.api.repositories.MatriculaRepository;
import com.onclass.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @PostMapping
    public ResponseEntity<Object> matricular(@RequestBody MatriculaRequestDTO data) {
        
        // 1. Busca o aluno no banco
        Optional<Usuario> alunoOpt = usuarioRepository.findById(data.alunoId());
        if (alunoOpt.isEmpty() || alunoOpt.get().getPerfil() != PerfilUsuario.ALUNO) {
            return ResponseEntity.badRequest().body("Erro: Aluno não encontrado ou usuário não tem perfil de ALUNO.");
        }

        // 2. Busca a disciplina no banco
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(data.disciplinaId());
        if (disciplinaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro: Disciplina não encontrada.");
        }

        // 3. Efetiva a matrícula
        Matricula novaMatricula = new Matricula();
        novaMatricula.setAluno(alunoOpt.get());
        novaMatricula.setDisciplina(disciplinaOpt.get());

        try {
            matriculaRepository.save(novaMatricula);
            return ResponseEntity.ok(novaMatricula);
        } catch (Exception e) {
            // Se o banco barrar por causa da constraint @UniqueConstraint, cai aqui
            return ResponseEntity.badRequest().body("Erro: Este aluno já está matriculado nesta disciplina.");
        }
    }
}