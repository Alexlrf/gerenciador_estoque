package br.com.teste.acoes;

import br.com.teste.infra.ConnectionFactory;
import br.com.teste.model.dao.ContatoDAO;
import br.com.teste.model.entity.ContatoUsuario;
import br.com.teste.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static br.com.teste.util.Constantes.MENSAGEM_ERRO_DESCONHECIDO;
import static br.com.teste.util.Constantes.MENSAGEM_ERRO_TRANSACAO_DB;

public class Listar implements IAcao {

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            ContatoDAO testeDAO = new ContatoDAO(connection);
            List<ContatoUsuario> contatos = testeDAO.buscarContatosUsuarios();
            req.setAttribute("contatos", contatos);
        } catch (SQLException e) {
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_TRANSACAO_DB);
        } catch (Exception e) {
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_DESCONHECIDO);
        }
        return "/WEB-INF/jsp/lista.jsp";
    }
}
