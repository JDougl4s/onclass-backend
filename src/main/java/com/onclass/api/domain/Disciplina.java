package com.onclass.api.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Disciplina")
@Table(name = "disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "carga_horaria_total", nullable = false)
    private Integer cargaHorariaTotal;

    @Column(name = "limite_faltas_permitido", nullable = false, precision = 5, scale = 2)
    private BigDecimal limiteFaltasPermitido;

    // Relacionamento: Muitas disciplinas podem pertencer a um mesmo professor
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Usuario professor;

    public Disciplina() {}


    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }

    public void setCargaHorariaTotal(Integer cargaHorariaTotal) {
        this.cargaHorariaTotal = cargaHorariaTotal;
    }

    public BigDecimal getLimiteFaltasPermitido() {
        return limiteFaltasPermitido;
    }

    public void setLimiteFaltasPermitido(BigDecimal limiteFaltasPermitido) {
        this.limiteFaltasPermitido = limiteFaltasPermitido;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }
    
}