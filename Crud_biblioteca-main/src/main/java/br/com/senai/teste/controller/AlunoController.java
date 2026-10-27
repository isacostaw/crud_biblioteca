package br.com.senai.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.senai.teste.model.Aluno;
import br.com.senai.teste.service.AlunoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    @PostMapping 
    public ResponseEntity<Aluno> cadastrarAluno(@Valid @RequestBody  Aluno aluno) {

        Aluno alunoCadastrado = alunoService.cadastrarAluno(aluno);
        return new ResponseEntity<>(alunoCadastrado, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoService.listarAlunos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable int id) {
        Aluno aluno = alunoService.buscarAlunoPorId(id);
        if (aluno != null) {
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping ("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable int id, @Valid @RequestBody Aluno novosDados) {
        final Optional<Aluno> alunoAtualizado = alunoService.atualizarAluno(id, novosDados);
        if (alunoAtualizado.isPresent()) {
            return ResponseEntity.ok(alunoAtualizado.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        boolean deletado = alunoService.deletarAluno(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
