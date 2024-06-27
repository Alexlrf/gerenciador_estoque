package br.com.gerenciadorestoque.infra;

public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem){
        super(mensagem);
    }

}
