package br.com.teste.model.dao;

import br.com.teste.infra.GenericException;
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
    public String cadastrar(ContatoUsuario contato) {
        String sql = "INSERT INTO public.usuario_teste_jsp (nome, email, tipo) VALUES (?, ?, ?)";
        ResultSet rs = null;
        long generatedId;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setString(2, contato.getEmail());
            preparedStatement.setString(3, contato.getTipo());
            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            generatedId = rs.getLong("id");
            return "Inserção criada com registro: " + generatedId;
        } catch (Exception e) {
            this.loggerErro(e, "cadastrar");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                this.loggerErro(e, "cadastrar");
            }
        }
        return String.format("Erro ao salvar registro de nome: %s", contato.getNome());
    }

    @Override
    public List<ContatoUsuario> buscarContatosUsuarios() {
        String sql = "Select * from public.usuario_teste_jsp order by id";
        List<ContatoUsuario> contatos = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                ContatoUsuario contatoUsuario = new ContatoUsuario();
                contatoUsuario.setId(rs.getLong("id"));
                contatoUsuario.setNome(rs.getString("nome"));
                contatoUsuario.setEmail(rs.getString("email"));
                contatoUsuario.setTipo(rs.getString("tipo"));
                contatos.add(contatoUsuario);
            }
        } catch (Exception e) {
            this.loggerErro(e, "buscarContatosUsuarios");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                this.loggerErro(e, "buscarContatosUsuarios");
            }
        }
        return contatos;
    }

    @Override
    public String alterar(String id, ContatoUsuario usuario) {
        String sql = "Update public.usuario_teste_jsp set nome = ?, email = ?, tipo = ? where id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getTipo());
            statement.setLong(4, Long.parseLong(id));
            statement.executeUpdate();
            return "Registro alterado com sucesso";
        } catch (Exception e) {
            this.loggerErro(e, "alterar");
        }
        return String.format("Erro ao alterar registro de nome: %s", usuario.getNome());
    }

    @Override
    public String excluirContatoUsuario(Long id) {
        String sql = "delete from public.usuario_teste_jsp where id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return "Registro de código: " + id + " excluído com sucesso";
        } catch (Exception e) {
            this.loggerErro(e, "excluirContatoUsuario");
        }
        return "Erro ao excluir registro de código: " + id;
    }

    @Override
    public List<ContatoUsuario> buscarContatosPorTipo(String tipoPessoa) {
        List<ContatoUsuario> contatos = new ArrayList<>();
        ResultSet rs = null;
        String sql = "select * from public.usuario_teste_jsp where tipo = ? order by id";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, tipoPessoa);
            rs = statement.executeQuery();
            while (rs.next()) {
                ContatoUsuario contato = new ContatoUsuario();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setTipo(rs.getString("tipo"));
                contatos.add(contato);
            }
        } catch (Exception e) {
            this.loggerErro(e, "buscarContatosPorTipo");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                this.loggerErro(e, "buscarContatosPorTipo");
            }
        }
        return contatos;
    }

    @Override
    public List<ContatoUsuario> buscarContatosPorFragmentoTexto(String fragmentoTexto) {
        List<ContatoUsuario> contatos = new ArrayList<>();
        ResultSet rs = null;
        String sql = "select * from public.usuario_teste_jsp where nome like ? order by id";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, '%' + fragmentoTexto + '%');
            rs = statement.executeQuery();
            while (rs.next()) {
                ContatoUsuario contato = new ContatoUsuario();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setTipo(rs.getString("tipo"));
                contatos.add(contato);
            }
        } catch (Exception e) {
            this.loggerErro(e, "buscarContatosPorFragmentoTexto");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                this.loggerErro(e, "buscarContatosPorFragmentoTexto");
            }
        }
        return contatos;
    }

    private void loggerErro(Throwable e, String buscarContatosPorTipo) {
        logger.error(String.format("Método: %s | Exception: [ %s ] | Mensagem: %s", buscarContatosPorTipo, e.getClass().getSimpleName(), e.getMessage()));
    }

}
