package com.onclass.api.dtos;

import com.onclass.api.domain.PerfilUsuario;

public record UsuarioRequestDTO(
    String nome, 
    String matricula, 
    String senha, 
    PerfilUsuario perfil
) {}