package br.com.teste.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acaoNome = req.getParameter("redirect");
        RequestDispatcher dispatcher =  req.getRequestDispatcher("/WEB-INF/jsp/"+acaoNome+".jsp");
        dispatcher.forward(req, resp);
    }
}
