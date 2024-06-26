package br.com.gerenciadorestoque.model.dao;

import br.com.gerenciadorestoque.model.entity.Pessoa;

import java.util.List;

public interface IPessoaDAO {

    String cadastrar(Pessoa usuario);

    List<Pessoa> buscarContatosUsuarios();

    public String alterar(String id, Pessoa usuario);

    String excluirContatoUsuario(Long id);

    List<Pessoa> buscarContatosPorTipo(String tipoPessoa);

    List<Pessoa> buscarContatosPorFragmentoTexto(String fragmentoTexto);
}
