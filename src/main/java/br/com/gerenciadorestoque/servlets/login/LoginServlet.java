package br.com.gerenciadorestoque.servlets.login;


import br.com.gerenciadorestoque.acoes.usuario.Buscar;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_LOGGER_EXCEPTION;
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


        try{
            if (new Buscar().buscarUsuario(request, response)) {
                response.sendRedirect(request.getContextPath());
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } catch(IOException e) {
            logger.error(String.format(MENSAGEM_ERRO_LOGGER_EXCEPTION, e.getClass().getSimpleName(), e.getMessage()));
        }
    }
}
