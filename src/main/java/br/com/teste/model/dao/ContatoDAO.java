package br.com.teste.model.dao;

import br.com.teste.infra.GenericException;
import br.com.teste.infra.LoggerApp;
import br.com.teste.model.entity.ContatoUsuario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO implements IContatoDAO {
    private final Logger logger = LogManager.getLogger(ContatoDAO.class);

    private final Connection connection;

    public ContatoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String cadastrar(ContatoUsuario usuario) {
        long generatedId;
        try {
            String sql = "INSERT INTO public.usuario_teste_jsp (nome, email, tipo) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getTipo());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            generatedId = resultSet.getLong("id");
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new GenericException(e.getMessage());
        }
        return "Inserção criada com registro: " + generatedId;
    }

    @Override
    public List<ContatoUsuario> buscarContatosUsuarios() {
        List<ContatoUsuario> contatos = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sql = "Select * from public.usuario_teste_jsp order by id";
            statement = this.connection.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                ContatoUsuario contatoUsuario = new ContatoUsuario();
                contatoUsuario.setId(rs.getLong("id"));
                contatoUsuario.setNome(rs.getString("nome"));
                contatoUsuario.setEmail(rs.getString("email"));
                contatoUsuario.setTipo(rs.getString("tipo"));
                contatos.add(contatoUsuario);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return contatos;
    }

    @Override
    public String alterar(String id, ContatoUsuario usuario) {
        String sql = "Update public.usuario_teste_jsp set nome = ?, email = ?, tipo = ? where id = ?";
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getTipo());
            statement.setLong(4, Long.parseLong(id));
            statement.executeUpdate();

        } catch(Exception e) {
            logger.error(e.getMessage());
            return String.format("Erro ao alterar registro de nome: %s", usuario.getNome());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return "Registro alterado com sucesso";
    }

    @Override
    public String excluirContatoUsuario(Long id) {
        PreparedStatement statement = null;
        try {
            String sql = "delete from public.usuario_teste_jsp where id = ?";
            statement = this.connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            return "Registro de código: " + id + " excluído com sucesso";
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return "Erro ao excluir registro de código: " + id;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

}
