package br.com.gerenciadorestoque.acoes.pessoa;

import br.com.gerenciadorestoque.acoes.IAcao;
import br.com.gerenciadorestoque.infra.ConnectionFactory;
import br.com.gerenciadorestoque.infra.GenericException;
import br.com.gerenciadorestoque.infra.NegocioException;
import br.com.gerenciadorestoque.model.dao.PessoaDAO;
import br.com.gerenciadorestoque.model.entity.Pessoa;
import br.com.gerenciadorestoque.util.ParametrosRequestMultipart;
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

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            ParametrosRequestMultipart requestMultipart = new ParametrosRequestMultipart(req);
            Map.Entry<Map<String, String>, byte[]> parametros = requestMultipart.obterValoresRequestMultipart();
            Map<String, String> camposSimples = parametros.getKey();

            String nomePessoa  = camposSimples.get("nome").isBlank() ?  "" : camposSimples.get("nome");
            String emailPessoa = camposSimples.get("email").isBlank() ? "" : camposSimples.get("email");
            String tipoPessoa  = camposSimples.get("tipo").isBlank() ?  "" : camposSimples.get("tipo");
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
        return "/WEB-INF/pessoa/cadastro.jsp";
    }

}
