package br.com.teste.servlets;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    static final Logger logger = LogManager.getLogger(LogoutServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Fechando sistema.......");
            for (int i = 0; i < 2; i++) {
                String dir = "C:\\Users\\alf_a\\Desktop\\Servidor_tomcat_producao\\apache-tomcat-9.0.44\\bin\\shutdown.bat";
                Runtime.getRuntime().exec(dir);
                logger.info(String.format("Click: %s", i));
            }
            logger.info("Sistema fechado !");
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        }

    }
}
