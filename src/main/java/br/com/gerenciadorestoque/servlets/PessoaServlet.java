package br.com.gerenciadorestoque.servlets;

import br.com.gerenciadorestoque.acoes.IAcao;
import br.com.gerenciadorestoque.util.ParametrosRequestMultipart;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_LOGGER_EXCEPTION;

@WebServlet("/pessoa")
public class PessoaServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(PessoaServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String acaoNome;
        String pacote = "br.com.gerenciadorestoque.acoes.pessoa.";
        String retorno = "/WEB-INF/pessoa/";

        try {
            ParametrosRequestMultipart requestMultipart = new ParametrosRequestMultipart(req);
            Map.Entry<Map<String, String>, byte[]> parametros = requestMultipart.obterValoresRequestMultipart();
            Map<String, String> camposSimples = new HashMap<>();

            if (Optional.ofNullable(parametros).isPresent()) {
                camposSimples = parametros.getKey();
            }

            if(Optional.ofNullable(camposSimples.get("redirect")).isPresent() && !camposSimples.get("redirect").isBlank()) {
                retorno = retorno + camposSimples.get("redirect");
            } else {
                acaoNome = camposSimples.get("acao");
                Class<?> classe = Class.forName(pacote + acaoNome);
                IAcao acao = (IAcao) classe.newInstance();
                retorno = retorno + acao.execute(req, resp, parametros);
            }
            RequestDispatcher dispatcher =  req.getRequestDispatcher(retorno+ ".jsp");
            dispatcher.forward(req, resp);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException |
                 IOException e) {
            logger.error(String.format(MENSAGEM_ERRO_LOGGER_EXCEPTION, e.getClass().getSimpleName(), e.getMessage()));
        }
    }
}
