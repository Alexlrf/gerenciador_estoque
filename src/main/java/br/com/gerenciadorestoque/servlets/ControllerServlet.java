package br.com.gerenciadorestoque.servlets;

import br.com.gerenciadorestoque.acoes.IAcao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(ControllerServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String pacote = "br.com.gerenciadorestoque.acoes.pessoa.";
            String acaoNome = req.getParameter("acao");
            Class<?> classe = Class.forName(pacote + acaoNome);

            IAcao acao = (IAcao) classe.newInstance();
            String retorno = acao.execute(req, resp);
            RequestDispatcher dispatcher =  req.getRequestDispatcher(retorno);
            dispatcher.forward(req, resp);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException |
                 IOException e) {
            logger.error(String.format("Exception: [ %s ] Mensagem: %s", e.getClass().getSimpleName(), e.getMessage()));
        }
    }
}
