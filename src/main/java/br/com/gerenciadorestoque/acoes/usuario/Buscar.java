package br.com.gerenciadorestoque.acoes.usuario;

import br.com.gerenciadorestoque.infra.ConnectionFactory;
import br.com.gerenciadorestoque.model.dao.UsuarioDAO;
import br.com.gerenciadorestoque.model.entity.Usuario;
import br.com.gerenciadorestoque.util.RequestUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_DESCONHECIDO;
import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_TRANSACAO_DB;

public class Buscar {

    private final Logger logger = LogManager.getLogger(Buscar.class);

    public boolean buscarUsuario(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");

        Usuario usuario;
        try(Connection connection = ConnectionFactory.getConnection()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            usuario = usuarioDAO.buscarUsuario(login, senha);
            if (Optional.ofNullable(usuario.getLogin()).isPresent()) {
                HttpSession session = req.getSession();
                session.setAttribute("usuario", usuario.getLogin());
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_TRANSACAO_DB);
        } catch (Exception e) {
            logger.error(e.getMessage());
            RequestUtil.inputRetornoErro(req, MENSAGEM_ERRO_DESCONHECIDO);
        }
        return false;
    }
}
