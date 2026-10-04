package com.onclass.api.repositories;

import com.onclass.api.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    // Apenas herdando o JpaRepository, já ganhamos os métodos save(), findAll(), findById() e deleteById() de graça.
}