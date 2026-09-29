package com.onclass.api.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Presenca")
@Table(
    name = "presencas",
    uniqueConstraints = {
        // Regra de Ouro 2: Um aluno não pode registar presença duas vezes na mesma aula
        @UniqueConstraint(columnNames = {"sessao_aula_id", "aluno_id"})
    }
)
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A qual aula esta presença pertence
    @ManyToOne
    @JoinColumn(name = "sessao_aula_id", nullable = false)
    private SessaoAula sessaoAula;

    // Quem é o aluno que está a validar a presença
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Usuario aluno;

    // O momento exato da leitura do QR Code
    @Column(name = "timestamp_leitura", nullable = false)
    private LocalDateTime timestampLeitura = LocalDateTime.now();

    public Presenca() {}


    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SessaoAula getSessaoAula() {
        return sessaoAula;
    }

    public void setSessaoAula(SessaoAula sessaoAula) {
        this.sessaoAula = sessaoAula;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public LocalDateTime getTimestampLeitura() {
        return timestampLeitura;
    }

    public void setTimestampLeitura(LocalDateTime timestampLeitura) {
        this.timestampLeitura = timestampLeitura;
    }


}