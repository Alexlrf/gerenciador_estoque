package br.com.teste.model.dao;

import br.com.teste.infra.GenericException;
//import br.com.teste.infra.LoggerApp;
import br.com.teste.model.entity.ContatoUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO implements IContatoDAO {

    private final Connection connection;

    public ContatoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String cadastrar(String nome, String email) {
        long generatedId;
        try {
            String sql = "INSERT INTO public.usuario_teste_jsp (nome, email) VALUES (?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            generatedId = resultSet.getLong("id");
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            this.msgErro(ex.getMessage());
            throw new GenericException(ex.getMessage());
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
                contatos.add(contatoUsuario);
            }
        } catch (SQLException ex) {
            this.msgErro(ex.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                this.msgErro(ex.getMessage());
            }
        }
        return contatos;
    }

    @Override
    public String alterar(String id, String nome, String email) {
        String sql = "Update public.usuario_teste_jsp set nome = ?, email = ? where id = ?";
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setLong(3, Long.parseLong(id));
            statement.executeUpdate();

        } catch(Exception ex) {
            this.msgErro(ex.getMessage());
            return "Erro ao alterar registro de nome: " + nome;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                this.msgErro(ex.getMessage());
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
        } catch (SQLException ex) {
            this.msgErro(ex.getMessage());
            return "Erro ao excluir registro de código: " + id;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                this.msgErro(ex.getMessage());
            }
        }
    }

    private void msgErro(String msg) {
//        LoggerApp.erro(msg, this.getClass());
    }
}
