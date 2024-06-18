package br.com.teste.acoes;

import br.com.teste.infra.ConnectionFactory;
import br.com.teste.infra.NegocioException;
import br.com.teste.model.dao.ContatoDAO;
import br.com.teste.model.entity.ContatoUsuario;
import br.com.teste.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

import static br.com.teste.util.Constantes.MENSAGEM_ERRO_DESCONHECIDO;
import static br.com.teste.util.Constantes.MENSAGEM_ERRO_TRANSACAO_DB;

public class Alterar implements IAcao {

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            String id = req.getParameter("idContato");
            ContatoUsuario usuario = new ContatoUsuario(
                RequestUtil.obterValorRequest(req, "nome"),
                RequestUtil.obterValorRequest(req, "email"),
                RequestUtil.obterValorRequest(req, "tipo")
            );

            ContatoDAO alteracao = new ContatoDAO(connection);
            String retorno = alteracao.alterar(id, usuario);
            RequestUtil.inputRetornoSucesso(req, retorno);
        } catch(NegocioException e) {
            RequestUtil.inputRetornoErro(req, e.getMessage());
        } catch (SQLException e) {
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_TRANSACAO_DB);
        } catch (Exception e) {
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_DESCONHECIDO);
        }
        return "/index.jsp";
    }
}
