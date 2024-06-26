package br.com.gerenciadorestoque.acoes.pessoa;

import br.com.gerenciadorestoque.acoes.IAcao;
import br.com.gerenciadorestoque.infra.ConnectionFactory;
import br.com.gerenciadorestoque.model.dao.PessoaDAO;
import br.com.gerenciadorestoque.model.entity.ContatoUsuario;
import br.com.gerenciadorestoque.model.enums.BuscasPessoasEnum;
import br.com.gerenciadorestoque.util.RequestUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_DESCONHECIDO;
import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_TRANSACAO_DB;

public class Listar implements IAcao {
    private final Logger logger = LogManager.getLogger(Listar.class);

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PessoaDAO testeDAO = new PessoaDAO(connection);
            List<ContatoUsuario> contatos = new ArrayList<>();

            String tipoBusca = req.getParameter("tipoBusca") != null ? req.getParameter("tipoBusca") : "TODOS";
            String textoBusca = req.getParameter("valorBusca") != null ? req.getParameter("valorBusca") : "";
            contatos = BuscasPessoasEnum.valueOf(tipoBusca).buscarPessoas(testeDAO, textoBusca);

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
