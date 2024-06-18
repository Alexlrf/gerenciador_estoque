package br.com.teste.infra;

public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem){
        super(mensagem);
    }

}
