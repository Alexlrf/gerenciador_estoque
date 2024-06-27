package br.com.gerenciadorestoque.servlets;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_LOGGER_EXCEPTION;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(RedirectServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String acaoNome = req.getParameter("redirect");
            RequestDispatcher dispatcher =  req.getRequestDispatcher("/WEB-INF/pessoa/"+acaoNome+".jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(String.format(MENSAGEM_ERRO_LOGGER_EXCEPTION, e.getClass().getSimpleName(), e.getMessage()));
        }
    }
}
