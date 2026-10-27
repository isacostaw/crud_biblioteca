package br.com.senai.teste.model;

import jakarta.persistence.Entity;  
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;  
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "alunos")

public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @NotBlank(message = "O nome do aluno não pode ser vazio.")

    private String nome;
    @NotBlank (message = "O nome do aluno não pode ser vazio.")
    
    @NotBlank (message = "O email do aluno não pode ser vazio.")
    @Email (message = "O email do aluno deve ser válido.")
    private String email;

    // Getters and Setters
    public int getId() {
        return id;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
