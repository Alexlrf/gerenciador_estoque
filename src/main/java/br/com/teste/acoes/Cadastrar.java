package br.com.teste.acoes;

import br.com.teste.model.dao.TesteDAO;
import br.com.teste.infra.ConnectionFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class Cadastrar implements Acao{

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        Connection connection = ConnectionFactory.getConnection();
        TesteDAO testeCadastro = new TesteDAO(connection);
        String ret = testeCadastro.cadastrar(nome, email);
        req.setAttribute("ret", ret);
        return "/WEB-INF/jsp/cadastro.jsp";
    }
}
