package com.onclass.api.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "SessaoAula")
@Table(name = "sessoes_aula")
public class SessaoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A qual disciplina esta aula pertence
    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    // Regista o exato momento em que a aula começou
    @Column(name = "data_hora_abertura", nullable = false)
    private LocalDateTime dataHoraAbertura = LocalDateTime.now();

    // Define se o QR Code ainda está a ser projetado ou se o professor já encerrou a chamada
    @Column(name = "status_ativa", nullable = false)
    private Boolean statusAtiva = true;

    public SessaoAula() {}


    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public LocalDateTime getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    public void setDataHoraAbertura(LocalDateTime dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    public Boolean getStatusAtiva() {
        return statusAtiva;
    }

    public void setStatusAtiva(Boolean statusAtiva) {
        this.statusAtiva = statusAtiva;
    }

    
}