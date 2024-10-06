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
        String sql = "INSERT INTO public.usuario_teste_jsp (nome, email, tipo, imagem_pessoa) VALUES (?, ?, ?, ?)";
        ResultSet rs = null;
        long generatedId;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getEmail());
            preparedStatement.setString(3, pessoa.getTipo());
            preparedStatement.setBytes(4, pessoa.getImagemPessoa());
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
    public List<Pessoa> buscarContatosUsuarios() {
        String sql = "Select id, nome, email, tipo from public.usuario_teste_jsp order by id";
        return this.buscarPessoas(sql, "");
    }

    @Override
    public List<Pessoa> buscarContatosPorTipo(String tipoPessoa) {
        StringBuilder sql = new StringBuilder("Select id, nome, email, tipo From public.usuario_teste_jsp where ");
        if (tipoPessoa.equalsIgnoreCase("Todos")) {
            sql.append("tipo in ('Fornecedor', 'Cliente')");
            tipoPessoa = "";
        } else {
            sql.append("tipo = ?");
        }
        sql.append("order by nome");
        return this.buscarPessoas(sql.toString(), tipoPessoa);
    }

    @Override
    public List<Pessoa> buscarContatosPorFragmentoTexto(String fragmentoTexto) {
        String sql = "select id, nome, email, tipo from public.usuario_teste_jsp where nome like ? order by id";
        String fragmentoTratado = '%' + fragmentoTexto + '%';
        return this.buscarPessoas(sql, fragmentoTratado);
    }

    private List<Pessoa> buscarPessoas(String sql, String param) {
        List<Pessoa> pessoas = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            if (!param.isEmpty()) {
                statement.setString(1, param);
            }
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

    public byte[] buscarImagemPessoa(Long idPessoa) {
        String sql = "SELECT imagem_pessoa from public.usuario_teste_jsp WHERE id = ?";
        ResultSet rs = null;
        byte[] imgRetorno = new byte[0];
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setLong(1, idPessoa);
            rs = statement.executeQuery();
            while (rs.next()) {
                imgRetorno = rs.getBytes("imagem_pessoa");
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
        return imgRetorno;
    }

    private void loggerErro(Throwable e) {
        logger.error(String.format(MENSAGEM_ERRO_LOGGER_EXCEPTION, e.getClass().getSimpleName(), e.getMessage()));
    }

}
