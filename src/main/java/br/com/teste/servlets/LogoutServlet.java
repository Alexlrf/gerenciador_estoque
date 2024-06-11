package br.com.teste.servlets;

import br.com.teste.infra.LoggerApp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            LoggerApp.info("Fechando sistema.......", this.getClass());
            System.out.println("Fechando sistema.......");
            String dir = "C:\\Users\\alf_a\\Desktop\\Servidor_tomcat_producao\\apache-tomcat-9.0.44\\bin\\shutdown.bat";
            Runtime.getRuntime().exec(dir);
            System.out.println("Sistema fechado !");
        } catch(Exception ex) {
            LoggerApp.erro(ex.getMessage(), this.getClass());
        }

    }
}
