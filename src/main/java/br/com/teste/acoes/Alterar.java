package br.com.teste.acoes;

import br.com.teste.infra.ConnectionFactory;
import br.com.teste.model.dao.TesteDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class Alterar implements Acao{

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("idContato");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        Connection connection = ConnectionFactory.getConnection();
        TesteDAO alteracao = new TesteDAO(connection);
        String ret = alteracao.alterar(id, nome, email);
        req.setAttribute("ret", ret);
        return "/index.jsp";
    }
}
