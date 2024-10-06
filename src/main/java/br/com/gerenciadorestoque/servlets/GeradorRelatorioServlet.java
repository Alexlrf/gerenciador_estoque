package br.com.gerenciadorestoque.servlets;

import br.com.gerenciadorestoque.infra.ConnectionFactory;
import br.com.gerenciadorestoque.util.GeradorDeRelatorios;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_LOGGER_EXCEPTION;

@WebServlet("/relatorio")
public class GeradorRelatorioServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(GeradorRelatorioServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            ServletContext contexto = req.getServletContext();
            String jrxml = contexto.getRealPath("/report/pessoa.jasper");

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("NOME", req.getParameter("nome"));

            resp.setHeader("Content-Disposition", "inline; filename=MeuRelatorio.pdf");
            resp.setContentType("application/pdf");

            GeradorDeRelatorios gerador = new GeradorDeRelatorios(connection);
            gerador.geraPdf(jrxml, parametros, resp.getOutputStream());
        } catch (IOException | RuntimeException | SQLException e) {
            logger.error(String.format(MENSAGEM_ERRO_LOGGER_EXCEPTION, e.getClass().getSimpleName(), e.getMessage()));
        }
    }

}
