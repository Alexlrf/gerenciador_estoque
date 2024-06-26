package br.com.gerenciadorestoque.model.dao;

import br.com.gerenciadorestoque.model.entity.ContatoUsuario;

import java.util.List;

public interface IPessoaDAO {

    String cadastrar(ContatoUsuario usuario);

    List<ContatoUsuario> buscarContatosUsuarios();

    public String alterar(String id, ContatoUsuario usuario);

    String excluirContatoUsuario(Long id);

    List<ContatoUsuario> buscarContatosPorTipo(String tipoPessoa);

    List<ContatoUsuario> buscarContatosPorFragmentoTexto(String fragmentoTexto);
}
