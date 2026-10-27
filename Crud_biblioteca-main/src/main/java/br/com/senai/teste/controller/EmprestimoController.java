package br.com.senai.teste.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;


import br.com.senai.teste.model.Emprestimo;
import br.com.senai.teste.service.EmprestimoService;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.Valid;
import br.com. senai.teste.dto.EmprestimoRequest;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    
    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping

public ResponseEntity<Emprestimo>cadastrar(@Valid @RequestBody EmprestimoRequest dados){
    
    Integer alunoId = dados.getAlunoId();
    Integer livroId = dados.getLivroId();
    LocalDate dataPrevistaDevolucao = dados.getDataPrevistaDevolucao();

    if (alunoId == null || livroId == null
        || dataPrevistaDevolucao == null
    ) {
        return ResponseEntity.badRequest().build();
    }
    Optional<Emprestimo> emprestimo = emprestimoService.cadastrar(alunoId, livroId, dataPrevistaDevolucao);

    if (emprestimo.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo.get());
}
@GetMapping ("/{id}")
public ResponseEntity<Emprestimo> buscarEmprestimoPorId(Integer id) {
    Optional<Emprestimo> emprestimo = emprestimoService.buscarPorId(id);

    if (emprestimo.isPresent()) {
        return  ResponseEntity.ok(emprestimo.get());
    } return ResponseEntity.notFound().build();
}

@PatchMapping ("/{id}/devolucao")
public ResponseEntity<Emprestimo> devolver(@PathVariable Integer id) {
    Optional<Emprestimo> emprestimo = emprestimoService.devolver(id);

    if (emprestimo.isPresent()) {
        return ResponseEntity.ok(emprestimo.get());
    } else {
        return ResponseEntity.notFound().build();
    }

}
@GetMapping ("/ativos")
public ResponseEntity<List<Emprestimo>> listarEmprestimosAtivos() {
    List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosAtivos();
    return ResponseEntity.ok(emprestimos);
}
@GetMapping ("/aluno/{alunoId}")
public ResponseEntity<List<Emprestimo>> listarPorAluno(@PathVariable Integer alunoId) {
    List<Emprestimo> emprestimos = emprestimoService.listarPorAluno(alunoId);
    return ResponseEntity.ok(emprestimos);
}
@GetMapping ("/livro/{livroId}")
public ResponseEntity<List<Emprestimo>> listarPorLivro(@PathVariable Integer livroId) {
    List<Emprestimo> emprestimos = emprestimoService.listarPorLivro(livroId);
    return ResponseEntity.ok(emprestimos);

}


@GetMapping ("/atrasados")
public ResponseEntity<List<Emprestimo>> listarEmprestimosAtrasados() {
    List<Emprestimo> emprestimosAtrasados = emprestimoService.listarEmprestimosAtrasados();
    return ResponseEntity.ok(emprestimosAtrasados);



}




}