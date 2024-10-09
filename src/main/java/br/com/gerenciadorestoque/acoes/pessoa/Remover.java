package br.com.gerenciadorestoque.acoes.pessoa;

import br.com.gerenciadorestoque.acoes.IAcao;
import br.com.gerenciadorestoque.infra.ConnectionFactory;
import br.com.gerenciadorestoque.model.dao.PessoaDAO;
import br.com.gerenciadorestoque.model.entity.Pessoa;
import br.com.gerenciadorestoque.util.RequestUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_DESCONHECIDO;
import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_TRANSACAO_DB;


public class Remover implements IAcao {
    private final Logger logger = LogManager.getLogger(Remover.class);
    public String execute(HttpServletRequest req, HttpServletResponse resp, Map.Entry<Map<String, String>, byte[]> camposSimples) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            Long idContatoUsuario = Long.valueOf(req.getParameter("id"));
            PessoaDAO dao = new PessoaDAO(connection);
            String retorno = dao.excluirContatoUsuario(idContatoUsuario);
            RequestUtil.inputRetornoSucesso(req, retorno);
            List<Pessoa> contatos = dao.buscarContatosUsuarios();
            req.setAttribute("contatos", contatos);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_TRANSACAO_DB);
        } catch (Exception e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_DESCONHECIDO);
        }
        return "/WEB-INF/pessoa/lista.jsp";
    }
}
