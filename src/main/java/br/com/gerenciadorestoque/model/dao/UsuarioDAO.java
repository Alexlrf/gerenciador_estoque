package br.com.gerenciadorestoque.model.dao;

import br.com.gerenciadorestoque.model.entity.Usuario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static br.com.gerenciadorestoque.util.Constantes.*;

public class UsuarioDAO {

    private final Logger logger = LogManager.getLogger(UsuarioDAO.class);
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public Usuario buscarUsuario(String loginParam, String senhaParam) {
        Usuario usuario = new Usuario();
        String queryString = "select login, senha from public.usuario where login = ? and senha = ?";
        ResultSet rs;
        try(PreparedStatement pstmt = this.connection.prepareStatement(queryString)) {
            pstmt.setString(1, loginParam);
            pstmt.setString(2, senhaParam);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }

        } catch (Exception e) {
            logger.error(String.format(MENSAGEM_ERRO_LOGGER_EXCEPTION, e.getClass().getSimpleName(), e.getMessage()));
        }
        return usuario;
    }
}
