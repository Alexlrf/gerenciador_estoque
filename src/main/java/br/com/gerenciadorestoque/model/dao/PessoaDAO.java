package br.com.gerenciadorestoque.model.dao;

import br.com.gerenciadorestoque.model.entity.Pessoa;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_LOGGER_EXCEPTION;

public class PessoaDAO implements IPessoaDAO {
    private final Logger logger = LogManager.getLogger(PessoaDAO.class);

    private final Connection connection;

    public PessoaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String cadastrar(Pessoa pessoa) {
        String sql = "INSERT INTO public.usuario_teste_jsp (nome, email, tipo) VALUES (?, ?, ?)";
        ResultSet rs = null;
        long generatedId;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getEmail());
            preparedStatement.setString(3, pessoa.getTipo());
            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            generatedId = rs.getLong("id");
            return "Inserção criada com registro: " + generatedId;
        } catch (Exception e) {
            this.loggerErro(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                this.loggerErro(e);
            }
        }
        return String.format("Erro ao salvar registro de nome: %s", pessoa.getNome());
    }

    @Override
    public List<Pessoa> buscarContatosUsuarios() {
        String sql = "Select * from public.usuario_teste_jsp order by id";
        List<Pessoa> pessoas = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getLong("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setTipo(rs.getString("tipo"));
                pessoas.add(pessoa);
            }
        } catch (Exception e) {
            this.loggerErro(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                this.loggerErro(e);
            }
        }
        return pessoas;
    }

    @Override
    public String alterar(String id, Pessoa pessoa) {
        String sql = "Update public.usuario_teste_jsp set nome = ?, email = ?, tipo = ? where id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getEmail());
            statement.setString(3, pessoa.getTipo());
            statement.setLong(4, Long.parseLong(id));
            statement.executeUpdate();
            return "Registro alterado com sucesso";
        } catch (Exception e) {
            this.loggerErro(e);
        }
        return String.format("Erro ao alterar registro de nome: %s", pessoa.getNome());
    }

    @Override
    public String excluirContatoUsuario(Long id) {
        String sql = "delete from public.usuario_teste_jsp where id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return "Registro de código: " + id + " excluído com sucesso";
        } catch (Exception e) {
            this.loggerErro(e);
        }
        return "Erro ao excluir registro de código: " + id;
    }

    @Override
    public List<Pessoa> buscarContatosPorTipo(String tipoPessoa) {
        List<Pessoa> pessoas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "select * from public.usuario_teste_jsp where tipo = ? order by id";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, tipoPessoa);
            rs = statement.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getLong("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setTipo(rs.getString("tipo"));
                pessoas.add(pessoa);
            }
        } catch (Exception e) {
            this.loggerErro(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                this.loggerErro(e);
            }
        }
        return pessoas;
    }

    @Override
    public List<Pessoa> buscarContatosPorFragmentoTexto(String fragmentoTexto) {
        List<Pessoa> pessoas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "select * from public.usuario_teste_jsp where nome like ? order by id";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, '%' + fragmentoTexto + '%');
            rs = statement.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getLong("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setTipo(rs.getString("tipo"));
                pessoas.add(pessoa);
            }
        } catch (Exception e) {
            this.loggerErro(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                this.loggerErro(e);
            }
        }
        return pessoas;
    }

    private void loggerErro(Throwable e) {
        logger.error(String.format(MENSAGEM_ERRO_LOGGER_EXCEPTION, e.getClass().getSimpleName(), e.getMessage()));
    }

}
