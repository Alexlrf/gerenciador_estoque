package br.com.gerenciadorestoque.acoes.pessoa;

import br.com.gerenciadorestoque.acoes.IAcao;
import br.com.gerenciadorestoque.infra.ConnectionFactory;
import br.com.gerenciadorestoque.model.dao.PessoaDAO;
import br.com.gerenciadorestoque.model.entity.Pessoa;
import br.com.gerenciadorestoque.model.enums.BuscasPessoasEnum;
import br.com.gerenciadorestoque.util.RequestUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_DESCONHECIDO;
import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_TRANSACAO_DB;

public class Listar implements IAcao {
    private final Logger logger = LogManager.getLogger(Listar.class);

    public String execute(HttpServletRequest req, HttpServletResponse resp, Map.Entry<Map<String, String>, byte[]> parametros) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PessoaDAO pessoaDAO = new PessoaDAO(connection);
            List<Pessoa> contatos;

            String tipoBusca  = Optional.ofNullable(parametros.getKey().get("tipoBusca")).isPresent() ? parametros.getKey().get("tipoBusca") : "TODOS";
            String textoBusca = Optional.ofNullable(parametros.getKey().get("valorBusca")).isPresent() ? parametros.getKey().get("valorBusca") : "";
            contatos = BuscasPessoasEnum.valueOf(tipoBusca).buscarPessoas(pessoaDAO, textoBusca);

            req.setAttribute("contatos", contatos);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_TRANSACAO_DB);
        } catch (Exception e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_DESCONHECIDO);
        }
        return "lista";
    }
}
