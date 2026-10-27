package br.com.senai.teste.exception;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class ValidacaoExceptionHandler {
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacao(MethodArgumentNotValidException erro) {
        Map<String, String> mensagens= new HashMap<>();
         erro.getBindingResult().getFieldErrors().forEach(erroCampo -> {
            mensagens.put(erroCampo.getField(), erroCampo.getDefaultMessage());

        });
        return ResponseEntity.badRequest().body(mensagens);
    }
}
