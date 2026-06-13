package br.edu.ifpi.clinica.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String mensagem) {
        super(mensagem);
    }
}
