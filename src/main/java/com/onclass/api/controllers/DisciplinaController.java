package com.onclass.api.controllers;

import com.onclass.api.domain.Disciplina;
import com.onclass.api.domain.Usuario;
import com.onclass.api.dtos.DisciplinaRequestDTO;
import com.onclass.api.repositories.DisciplinaRepository;
import com.onclass.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody DisciplinaRequestDTO data) {
        
        // 1. Verificamos se o professor existe no banco de dados
        Optional<Usuario> professorOpt = usuarioRepository.findById(data.professorId());
        
        if (professorOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro: Professor não encontrado com o ID informado.");
        }

        // 2. Montamos a Disciplina com os dados do DTO e o objeto do Professor
        Disciplina novaDisciplina = new Disciplina();
        novaDisciplina.setNome(data.nome());
        novaDisciplina.setCargaHorariaTotal(data.cargaHorariaTotal());
        novaDisciplina.setLimiteFaltasPermitido(data.limiteFaltasPermitido());
        novaDisciplina.setProfessor(professorOpt.get());

        // 3. Salvamos no banco
        disciplinaRepository.save(novaDisciplina);

        return ResponseEntity.ok(novaDisciplina);
    }
}