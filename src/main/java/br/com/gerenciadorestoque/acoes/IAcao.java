package br.com.gerenciadorestoque.acoes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface IAcao {
    String execute(HttpServletRequest req, HttpServletResponse resp, Map.Entry<Map<String, String>, byte[]> camposSimples) throws ServletException, IOException;
}
