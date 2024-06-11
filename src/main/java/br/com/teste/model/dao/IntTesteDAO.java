package br.com.teste.model.dao;

import br.com.teste.model.entity.ContatoUsuario;

import java.util.List;

public interface IntTesteDAO {

    String cadastrar(String nome, String email);
    List<ContatoUsuario> buscarContatosUsuarios();
    String alterar(String id, String nome, String email);
    String excluirContatoUsuario(Long id);
}
