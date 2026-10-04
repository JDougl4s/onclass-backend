package com.onclass.api.repositories;

import com.onclass.api.domain.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {
}