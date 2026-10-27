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
import br.com.senai.teste.model.Livro;
import br.com.senai.teste.service.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivrosController {
    private final LivroService livroService;
    public LivrosController(LivroService livroService) {
        this.livroService = livroService;
        
    }
    
           @PostMapping
    public ResponseEntity<Livro> cadastrarLivro(@Valid @RequestBody Livro livro) {
        Livro livroCadastrado = livroService.cadastrarLivro(livro);
        return  ResponseEntity
        .status(HttpStatus.CREATED)
        .body(livroCadastrado);
    }
    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = livroService.listarLivros();
        return ResponseEntity.ok(livros);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable int id) {
        Livro livro = livroService.buscarLivroPorId(id);
        if (livro != null) {
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable int id, @RequestBody Livro novosDados) {
        final Optional<Livro> livroAtualizado = livroService.atualizarLivro(id, novosDados);
        if (livroAtualizado.isPresent()) {
            return ResponseEntity.ok(livroAtualizado.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        boolean deletado = livroService.deletarLivro(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

}