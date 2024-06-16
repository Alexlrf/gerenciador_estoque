package br.com.teste.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private ConnectionFactory(){}
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost/teste_jsp_DB",
                    "postgres",
                    "root"
            );
        } catch (SQLException | ClassNotFoundException ex) {
//            LoggerApp.erro(ex.getMessage(), ConnectionFactory.class);
            throw new GenericException(ex.getMessage());
        }
    }
}
