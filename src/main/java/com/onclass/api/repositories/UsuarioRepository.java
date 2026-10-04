package com.onclass.api.repositories;

import com.onclass.api.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // O Spring cria o SELECT automaticamente só de lermos o nome do método!
    // Usaremos isso mais tarde para não deixar cadastrar duas pessoas com a mesma matrícula
    Optional<Usuario> findByMatricula(String matricula);
}