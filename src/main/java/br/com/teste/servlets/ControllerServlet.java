package br.com.teste.servlets;

import br.com.teste.acoes.Acao;
import br.com.teste.infra.LoggerApp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String pacote = "br.com.teste.acoes.";
            String acaoNome = req.getParameter("acao");
            Class<?> classe = Class.forName(pacote + acaoNome);

            Acao acao = (Acao) classe.newInstance();
            String retorno = acao.execute(req, resp);
            RequestDispatcher dispatcher =  req.getRequestDispatcher(retorno);
            dispatcher.forward(req, resp);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LoggerApp.erro(e.getMessage(), ControllerServlet.class);
        }
    }
}
