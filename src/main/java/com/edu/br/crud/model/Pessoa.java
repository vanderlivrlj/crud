package com.edu.br.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pessoa")
@AllArgsConstructor
public class Pessoa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Id
    private int id;

    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;
    @Column(name = "idade")
    @Getter
    @Setter
    private int idade;

    public Pessoa(){

    }
    public Pessoa (String nome, int idade) {
        super();
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
