package br.com.gerenciadorestoque.infra;

public class GenericException extends RuntimeException {

    public GenericException(String mensagem){
        super(mensagem);
    }

}
