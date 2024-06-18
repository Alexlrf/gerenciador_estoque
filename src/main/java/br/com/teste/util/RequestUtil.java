package br.com.teste.util;

import br.com.teste.infra.NegocioException;

import javax.servlet.http.HttpServletRequest;

import static br.com.teste.util.Constantes.*;
import static br.com.teste.util.Constantes.COR_MENSAGEM_SUCESSO;

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
