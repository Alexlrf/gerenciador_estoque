package br.com.gerenciadorestoque.servlets;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    static final Logger logger = LogManager.getLogger(LogoutServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            logger.info("Fechando sistema.......");
            for (int i = 0; i < 2; i++) {
                String dir = "C:\\Users\\alf_a\\Desktop\\Servidor_tomcat_producao\\apache-tomcat-9.0.44\\bin\\shutdown.bat";
                Runtime.getRuntime().exec(dir);
                logger.info(String.format("Click: %s", i));
            }
            logger.info("Sistema fechado !");
        } catch(Exception e) {
            logger.error(String.format("Exception: [ %s ] Mensagem: %s", e.getClass().getSimpleName(), e.getMessage()));
        }

    }
}
