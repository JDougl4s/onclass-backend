package com.onclass.api.domain;

import jakarta.persistence.*;

@Entity(name = "Matricula")
@Table(
    name = "matriculas", 
    uniqueConstraints = {
        // Esta é a regra de ouro: impede que o mesmo aluno seja matriculado duas vezes na mesma disciplina
        @UniqueConstraint(columnNames = {"aluno_id", "disciplina_id"})
    }
)
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com o Aluno (que é um Usuario)
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Usuario aluno;

    // Relacionamento com a Disciplina
    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    public Matricula() {}

    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

}