package br.com.gerenciadorestoque.servlets.login;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_LOGGER_EXCEPTION;

@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/login.jsp");

        } catch (IOException e) {
            logger.error(String.format(MENSAGEM_ERRO_LOGGER_EXCEPTION, e.getClass().getSimpleName(), e.getMessage()));
        }

    }
}
