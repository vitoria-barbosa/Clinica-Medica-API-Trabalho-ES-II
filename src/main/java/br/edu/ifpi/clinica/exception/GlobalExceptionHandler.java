package br.edu.ifpi.clinica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handleRecursoNaoEncontrado(RecursoNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<?> handleBDException(DatabaseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro no banco de dados: " + e.getMessage());
    }

    @ExceptionHandler(DadoInvalidoException.class)
    public ResponseEntity<?> handleDadoInvalido(DadoInvalidoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dado inválido: " + e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e) {

        Map<String, String> erros = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            String campo = error.getField();
            String mensagem = error.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erros de validação: " + erros);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleAllExceptions(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
    }
}
