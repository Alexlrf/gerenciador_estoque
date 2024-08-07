package br.com.gerenciadorestoque.infra;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoggerApp {

    private LoggerApp() {
    }
    static final Logger logger = LogManager.getLogger(LoggerApp.class);

    public static void info(String msg, Class<?> classe) {
        logger.info("Classe: [" + classe.getSimpleName() + "] Mensagem: " + msg);
    }

    public static void erro(String msg, Class<?> classe) {
        logger.error("Classe: [" + classe.getSimpleName() + "] Erro: " + msg);
    }
}