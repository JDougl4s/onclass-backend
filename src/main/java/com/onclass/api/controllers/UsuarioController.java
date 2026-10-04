package com.onclass.api.controllers;

import com.onclass.api.domain.Usuario;
import com.onclass.api.dtos.UsuarioRequestDTO;
import com.onclass.api.repositories.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Diz ao Spring que esta classe vai responder a requisições web (JSON)
@RequestMapping("/usuarios") // Define o endereço base desta rota
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping // Diz que este método responde ao verbo HTTP POST
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioRequestDTO data) {
        
        // 1. Convertemos o DTO que chegou da web numa Entidade real do banco
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(data.nome());
        novoUsuario.setMatricula(data.matricula());
        novoUsuario.setSenhaHash(data.senha()); // Mais tarde vamos criptografar isto!
        novoUsuario.setPerfil(data.perfil());

        // 2. Pedimos ao repositório para salvar no PostgreSQL
        repository.save(novoUsuario);

        // 3. Devolvemos o status 200 (OK) e o utilizador criado
        return ResponseEntity.ok(novoUsuario);
    }

    @GetMapping // Diz que este método responde ao verbo HTTP GET
    public ResponseEntity<List<Usuario>> listarTodos() {
        // Pedimos ao repositório para buscar todos os registros na tabela
        List<Usuario> usuarios = repository.findAll();
        
        // Devolvemos a lista com o status 200 OK
        return ResponseEntity.ok(usuarios);
    }
}