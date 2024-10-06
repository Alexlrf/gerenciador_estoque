package br.com.gerenciadorestoque.infra;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);

    private static final Dotenv dotenv =
            Dotenv
//                    .configure()
//                    .directory("/conf")
                    .load();
    private static final String DRIVER_BATABASE = dotenv.get("DRIVER_BATABASE");
    private static final String URL_DATABAE = dotenv.get("URL_BATABASE");
    private static final String USER_DB = dotenv.get("USER_DB");
    private static final String PASSWORD_DB = dotenv.get("PASSWORD_DB");


    private ConnectionFactory(){}
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_BATABASE);
            return DriverManager.getConnection(
                    Objects.requireNonNull(URL_DATABAE),
                    USER_DB,
                    PASSWORD_DB
            );
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new GenericException(String.format("Exception: [ %s ] Mensagem: %s", e.getClass().getSimpleName(), e.getMessage()));
        }
    }
}
