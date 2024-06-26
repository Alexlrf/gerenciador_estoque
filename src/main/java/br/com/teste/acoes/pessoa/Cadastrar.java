package br.com.teste.acoes.pessoa;

import br.com.teste.acoes.IAcao;
import br.com.teste.infra.NegocioException;
import br.com.teste.model.dao.PessoaDAO;
import br.com.teste.infra.ConnectionFactory;
import br.com.teste.model.entity.ContatoUsuario;
import br.com.teste.util.RequestUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

import static br.com.teste.util.Constantes.MENSAGEM_ERRO_DESCONHECIDO;
import static br.com.teste.util.Constantes.MENSAGEM_ERRO_TRANSACAO_DB;

public class Cadastrar implements IAcao {
    private final Logger logger = LogManager.getLogger(Cadastrar.class);
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            ContatoUsuario usuario = new ContatoUsuario(
                RequestUtil.obterValorRequest(req, "nome"),
                RequestUtil.obterValorRequest(req, "email"),
                RequestUtil.obterValorRequest(req, "tipo")
            );
            PessoaDAO testeCadastro = new PessoaDAO(connection);
            String retorno = testeCadastro.cadastrar(usuario);
            RequestUtil.inputRetornoSucesso(req, retorno);
        } catch (NegocioException e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_TRANSACAO_DB);
        } catch (Exception e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_DESCONHECIDO);
        }
        return "/WEB-INF/jsp/cadastro.jsp";
    }
}
