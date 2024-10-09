package br.com.gerenciadorestoque.acoes.pessoa;

import br.com.gerenciadorestoque.acoes.IAcao;
import br.com.gerenciadorestoque.infra.ConnectionFactory;
import br.com.gerenciadorestoque.infra.GenericException;
import br.com.gerenciadorestoque.infra.NegocioException;
import br.com.gerenciadorestoque.model.dao.PessoaDAO;
import br.com.gerenciadorestoque.model.entity.Pessoa;
import br.com.gerenciadorestoque.util.RequestUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_DESCONHECIDO;
import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_TRANSACAO_DB;

public class Cadastrar implements IAcao {
    private final Logger logger = LogManager.getLogger(Cadastrar.class);

    public String execute(HttpServletRequest req, HttpServletResponse resp, Map.Entry<Map<String, String>, byte[]> parametros) {
        try(Connection connection = ConnectionFactory.getConnection()) {

            String nomePessoa  = parametros.getKey().get("nome").isBlank() ?  "" : parametros.getKey().get("nome");
            String emailPessoa = parametros.getKey().get("email").isBlank() ? "" : parametros.getKey().get("email");
            String tipoPessoa  = parametros.getKey().get("tipo").isBlank() ?  "" : parametros.getKey().get("tipo");
            byte[] fileContent = parametros.getValue();

            Pessoa pessoa = new Pessoa(
                    nomePessoa,
                    emailPessoa,
                    tipoPessoa,
                    fileContent
            );

            PessoaDAO pessoaDAO = new PessoaDAO(connection);
            String retornoCadastro = pessoaDAO.cadastrar(pessoa);
            RequestUtil.inputRetornoSucesso(req, retornoCadastro);
        } catch (NegocioException | GenericException e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_TRANSACAO_DB);
        } catch (Exception e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_DESCONHECIDO);
        }
        return "cadastro";
    }

}
