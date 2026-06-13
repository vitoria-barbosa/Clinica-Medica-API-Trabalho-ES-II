package br.edu.ifpi.clinica.exception;

public class DadoInvalidoException extends RuntimeException {
    public DadoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
