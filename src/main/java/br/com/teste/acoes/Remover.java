package br.com.teste.acoes;

import br.com.teste.infra.ConnectionFactory;
import br.com.teste.model.dao.ContatoDAO;
import br.com.teste.model.entity.ContatoUsuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;

public class Remover implements IAcao {
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Connection connection = ConnectionFactory.getConnection();
        Long idContatoUsuario = Long.valueOf(req.getParameter("id"));
        ContatoDAO dao = new ContatoDAO(connection);
        String ret = dao.excluirContatoUsuario(idContatoUsuario);
        req.setAttribute("ret", ret);
        List<ContatoUsuario> contatos = dao.buscarContatosUsuarios();
        req.setAttribute("contatos", contatos);
        return "/WEB-INF/jsp/lista.jsp";
    }
}
