package br.com.gerenciadorestoque.util;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_LOGGER_EXCEPTION;

public class FuncoesUtil {

    private FuncoesUtil(){}

    public static String lancarLogErro(Throwable exception) {
        return String.format(
                MENSAGEM_ERRO_LOGGER_EXCEPTION
                , exception.getClass().getSimpleName()
                , exception.getMessage()
        );
    }
}
