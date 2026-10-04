package com.onclass.api.dtos;

public record PresencaRequestDTO(
    Long sessaoAulaId,
    Long alunoId
) {}