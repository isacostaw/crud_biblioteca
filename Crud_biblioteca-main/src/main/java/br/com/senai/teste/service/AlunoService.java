package br.com.senai.teste.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.teste.model.Aluno;
import br.com.senai.teste.repository.AlunoRepository;


@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAlunoPorId(int id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public Optional<Aluno> atualizarAluno(int id, Aluno novosDados) {
        Optional<Aluno> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            Aluno aluno = alunoExistente.get();
            aluno.setNome(novosDados.getNome());
        
            aluno.setEmail(novosDados.getEmail());
            Aluno alunoAtualizado = alunoRepository.save(aluno);
            return Optional.of(alunoAtualizado);
        } else {
            return Optional.empty();
        }
    }
    public boolean deletarAluno(int id) {
        Optional<Aluno> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            alunoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }   

    
    
}
