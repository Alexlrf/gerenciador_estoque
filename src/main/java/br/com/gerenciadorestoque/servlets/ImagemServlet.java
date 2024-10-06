package br.com.gerenciadorestoque.servlets;

import br.com.gerenciadorestoque.infra.ConnectionFactory;
import br.com.gerenciadorestoque.model.dao.PessoaDAO;
import br.com.gerenciadorestoque.util.RequestUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Optional;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static br.com.gerenciadorestoque.util.Constantes.ERRO_PROCESSAMENTO_ARQUIVO;
import static br.com.gerenciadorestoque.util.Constantes.MENSAGEM_ERRO_LOGGER_EXCEPTION;

@WebServlet("/imagem")
public class ImagemServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(ImagemServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String imageId = "0";
        if (Optional.ofNullable(request.getParameter("id")).isPresent()) {
            imageId = request.getParameter("id");
        }

        try (Connection connection = ConnectionFactory.getConnection();
             OutputStream outputStream = response.getOutputStream()) {
            byte[] imgData = new PessoaDAO(connection).buscarImagemPessoa(Long.valueOf(imageId));
            response.setContentType("image/png");
            response.setContentLength(imgData.length);
            outputStream.write(imgData);
        } catch (Exception e) {
            logger.error(String.format(MENSAGEM_ERRO_LOGGER_EXCEPTION, e.getClass().getSimpleName(), e.getMessage()));
            RequestUtil.inputRetornoErro(request, ERRO_PROCESSAMENTO_ARQUIVO);
        }
    }
}

