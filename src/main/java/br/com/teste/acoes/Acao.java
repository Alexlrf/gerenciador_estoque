package br.com.teste.acoes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Acao {

    String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
