package br.com.teste.model.enums;

import br.com.teste.model.dao.ContatoDAO;
import br.com.teste.model.entity.ContatoUsuario;

import java.util.List;

public enum BuscasPessoasEnum {

    TODOS () {
        @Override
        public List<ContatoUsuario> buscarPessoas(ContatoDAO testeDAO, String paramBusca) {
            return testeDAO.buscarContatosUsuarios();
        }
    },

    TIPO () {
        @Override
        public List<ContatoUsuario> buscarPessoas(ContatoDAO testeDAO, String paramBusca) {
            return testeDAO.buscarContatosPorTipo(paramBusca);
        }
    },

    FRAGMENTO_TEXTO () {
        @Override
        public List<ContatoUsuario> buscarPessoas(ContatoDAO testeDAO, String paramBusca) {
            return testeDAO.buscarContatosPorFragmentoTexto(paramBusca);
        }
    };

    public abstract List<ContatoUsuario> buscarPessoas(ContatoDAO testeDAO, String paramBusca);

    BuscasPessoasEnum(){}
}
