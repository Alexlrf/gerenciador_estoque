package br.com.gerenciadorestoque.util;

import br.com.gerenciadorestoque.infra.NegocioException;

import javax.servlet.http.HttpServletRequest;

import static br.com.gerenciadorestoque.util.Constantes.*;
import static br.com.gerenciadorestoque.util.Constantes.COR_MENSAGEM_SUCESSO;

public class RequestUtil {

    private RequestUtil() {}

    public static String obterValorRequest(HttpServletRequest req, String valorParam) {
        if(req.getParameter(valorParam) == null || req.getParameter(valorParam).isEmpty()) {
            throw new NegocioException(String.format(MENSAGEM_ERRO_CAMPO_VAZIO, valorParam));
        }
        return req.getParameter(valorParam).trim();
    }

    public static void inputRetornoSucesso(HttpServletRequest req, String msg) {
        req.setAttribute(KEY_MSG_RETORNO, msg);
        req.setAttribute(KEY_COR_MENSAGEM_RETORNO, COR_MENSAGEM_SUCESSO);
    }

    public static void inputRetornoErro(HttpServletRequest req, String msg) {
        req.setAttribute(KEY_MSG_RETORNO, msg);
        req.setAttribute(KEY_COR_MENSAGEM_RETORNO, COR_MENSAGEM_ERRO);
    }
}
