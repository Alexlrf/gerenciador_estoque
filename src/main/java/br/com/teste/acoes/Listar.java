package br.com.teste.acoes;

import br.com.teste.infra.ConnectionFactory;
import br.com.teste.model.dao.TesteDAO;
import br.com.teste.model.entity.ContatoUsuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;

public class Listar implements Acao{

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Connection connection = ConnectionFactory.getConnection();
        TesteDAO testeDAO = new TesteDAO(connection);
        List<ContatoUsuario> contatos = testeDAO.buscarContatosUsuarios();
        req.setAttribute("contatos", contatos);
        return "/WEB-INF/jsp/lista.jsp";
    }
}
