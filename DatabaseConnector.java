package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/java";
    private static final String UTILISATEUR = "Amy";
    private static final String mdp = "loum";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Pilote JDBC non trouvé");
        }

        return DriverManager.getConnection(URL, UTILISATEUR, mdp);
    }
}
