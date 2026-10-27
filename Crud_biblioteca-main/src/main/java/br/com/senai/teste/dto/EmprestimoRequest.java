package br.com.senai.teste.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class EmprestimoRequest {
    @NotNull (message = "O ID do livro não pode ser nulo.")
    private Integer livroId;
    @NotNull (message = "O ID do aluno não pode ser nulo.")
    private Integer alunoId;
    @NotNull (message = "A data prevista de devolução não pode ser nula.")
    @Future (message = "A data prevista de devolução deve ser uma data futura.")
    private LocalDate dataPrevistaDevolucao;

    public EmprestimoRequest() {
    }

    public Integer getLivroId() {
        return livroId;
    }
    public void setIdLivro(Integer livroId) {
        this.livroId = livroId;
    }
    public Integer getAlunoId() {
        return alunoId;
    }
    public void setIdAluno(Integer idAluno) {
        this.alunoId = alunoId;
    }
    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }
    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }



}
