package br.com.gerenciadorestoque.model.enums;

import br.com.gerenciadorestoque.model.dao.PessoaDAO;
import br.com.gerenciadorestoque.model.entity.Pessoa;

import java.util.List;

public enum BuscasPessoasEnum {

    TODOS () {
        @Override
        public List<Pessoa> buscarPessoas(PessoaDAO testeDAO, String paramBusca) {
            return testeDAO.buscarContatosUsuarios();
        }
    },

    TIPO () {
        @Override
        public List<Pessoa> buscarPessoas(PessoaDAO testeDAO, String paramBusca) {
            return testeDAO.buscarContatosPorTipo(paramBusca);
        }
    },

    FRAGMENTO_TEXTO () {
        @Override
        public List<Pessoa> buscarPessoas(PessoaDAO testeDAO, String paramBusca) {
            return testeDAO.buscarContatosPorFragmentoTexto(paramBusca);
        }
    };

    public abstract List<Pessoa> buscarPessoas(PessoaDAO testeDAO, String paramBusca);

    BuscasPessoasEnum(){}
}
