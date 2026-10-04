package com.onclass.api.dtos;

import java.math.BigDecimal;

public record DisciplinaRequestDTO(
    String nome,
    Integer cargaHorariaTotal,
    BigDecimal limiteFaltasPermitido,
    Long professorId // Recebemos apenas o ID numérico na requisição
) {}