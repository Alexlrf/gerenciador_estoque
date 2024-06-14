package br.com.teste.acoes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IAcao {

    String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
